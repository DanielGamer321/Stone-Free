package com.danielgamer321.rotp_sf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUBarrierEntity;
import com.github.standobyte.jojo.util.general.GraphAdjacencyList;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class SFBarriersNet {
	private Map<SFUBarrierEntity, ShootingPoints> placedBarriers = new HashMap<>();
	private Map<SFUBarrierEntity, ShootingPoints> barriersRemoved = new HashMap<>();
	private GraphAdjacencyList<Vector3d> closePoints = new GraphAdjacencyList<>();
	private double lastShotGap = -1;

	public void tick() {
		Iterator<Map.Entry<SFUBarrierEntity, ShootingPoints>> iter = placedBarriers.entrySet().iterator();
		Iterator<Map.Entry<SFUBarrierEntity, ShootingPoints>> negativeIter = barriersRemoved.entrySet().iterator();
        while (iter.hasNext()) {
            SFUBarrierEntity barrier = iter.next().getKey();
            if (!barrier.isAlive() || barrier.wasRipped()) {
            	onRemoved(barrier);
                iter.remove();
            }
        }
		while (negativeIter.hasNext()) {
			SFUBarrierEntity barrier = negativeIter.next().getKey();
			if (!barrier.isAlive() || barrier.wasRipped()) {
				onRemoved(barrier);
				negativeIter.remove();
			}
		}
	}
	
	public void add(SFUBarrierEntity barrier) {
		placedBarriers.put(barrier, generateShootingPoints(barrier));
		onUpdate();
	}

	public void addNegative(SFUBarrierEntity barrier) {
		barriersRemoved.put(barrier, generateShootingPoints(barrier));
		onUpdate();
	}
	
	private static final double SHOOTING_POINTS_GAP = 8;
	private ShootingPoints generateShootingPoints(SFUBarrierEntity entity) {
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

	private void onRemoved(SFUBarrierEntity barrier) {

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

	public int getNegativeSize() {
		return barriersRemoved.size();
	}
    
    public Stream<Vector3d> wasAlertedAt() {
        return placedBarriers.keySet().stream()
                .flatMap(barrier -> barrier.wasAlertedAt().map(point -> Stream.of(point)).orElse(Stream.empty()));
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
