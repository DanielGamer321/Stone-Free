package com.danielgamer321.rotp_sf.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFBarrierEntity;
import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.general.GeneralUtil;
import com.github.standobyte.jojo.util.general.GraphAdjacencyList;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class SFBarriersNet {
	private Map<SFBarrierEntity, ShootingPoints> placedBarriers = new HashMap<>();
	private GraphAdjacencyList<Vector3d> closePoints = new GraphAdjacencyList<>();
	private double lastShotGap = -1;
    private boolean canShoot;

	public void tick() {
        Iterator<Map.Entry<SFBarrierEntity, ShootingPoints>> iter = placedBarriers.entrySet().iterator();
        while (iter.hasNext()) {
            SFBarrierEntity barrier = iter.next().getKey();
            if (!barrier.isAlive() || barrier.wasRipped()) {
            	onRemoved(barrier);
                iter.remove();
            }
        }
        canShoot = true;
	}
	
	public void add(SFBarrierEntity barrier) {
		placedBarriers.put(barrier, generateShootingPoints(barrier));
		onUpdate();
	}
	
	private static final double SHOOTING_POINTS_GAP = 8;
	private ShootingPoints generateShootingPoints(SFBarrierEntity entity) {
		Vector3d posA = entity.position();
		Vector3d posB = entity.getOriginPoint(1.0F);
		Vector3d vecAToB = posB.subtract(posA);
		
		List<Vector3d> shootingPoints = new ArrayList<>();
		if (vecAToB.lengthSqr() <= SHOOTING_POINTS_GAP * SHOOTING_POINTS_GAP * 4) {
			shootingPoints.add(posA.add(vecAToB.scale(0.5)));
		}
		else {
			int steps = MathHelper.floor(vecAToB.length() / SHOOTING_POINTS_GAP);
			Vector3d nextPoint = posA;
			Vector3d stepVec = vecAToB.normalize().scale(SHOOTING_POINTS_GAP);
			for (int i = 0; i < steps; i++) {
				nextPoint = nextPoint.add(stepVec);
				shootingPoints.add(nextPoint);
			}
		}

		return new ShootingPoints(shootingPoints);
	}
	
	private void onRemoved(SFBarrierEntity barrier) {
		
		onUpdate();
	}
	
	private void onUpdate() {
		if (lastShotGap > -1) {
			closePoints.clear();
			lastShotGap = -1;
		}
	}
	
	public int getSize() {
		return placedBarriers.size();
	}

    public void shootStringsFromBarriers(IStandPower standPower, StoneFreeEntity stand, 
    		Vector3d targetPos, int tick, double maxStrings, float staminaPerString, double minGap, boolean breakBlocks) {
    	if (!canShoot) return;
    	List<Vector3d> shootingPoints = placedBarriers.values().stream().flatMap(points -> 
    	points.shootingPoints.stream()).collect(Collectors.toCollection(LinkedList::new));
    	if (lastShotGap != minGap) {
    		closePoints.create((pointA, pointB) -> pointA.distanceToSqr(pointB) < minGap * minGap, shootingPoints);
    		lastShotGap = minGap;
    	}

    	Set<Vector3d> pointsToShootThisTick = new HashSet<>();
    	GeneralUtil.doFractionTimes(() -> {
    		Vector3d point = shootingPoints.stream().min(Comparator.comparingDouble(p -> p.distanceToSqr(targetPos))).get();
    		pointsToShootThisTick.add(point);
    		shootingPoints.remove(point);
    		closePoints.getAllAdjacent(point).forEach(closePoint -> shootingPoints.remove(closePoint));
    	}, maxStrings, () -> shootingPoints.isEmpty());
    	
    	canShoot = false;
    }
    
    public Stream<Vector3d> wasRippedAt() {
        return placedBarriers.keySet().stream()
                .flatMap(barrier -> barrier.wasRippedAt().map(point -> Stream.of(point)).orElse(Stream.empty()));
    }
    
    public enum PointsChoice {
    	RANDOM,
    	CLOSEST
    }
    
    private class ShootingPoints {
    	private final List<Vector3d> shootingPoints;
    	
    	private ShootingPoints(List<Vector3d> shootingPoints) {
    		this.shootingPoints = shootingPoints;
    	}
    }
}
