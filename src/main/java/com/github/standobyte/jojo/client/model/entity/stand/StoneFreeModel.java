package com.github.standobyte.jojo.client.model.entity.stand;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.client.model.pose.IModelPose;
import com.github.standobyte.jojo.client.model.pose.ModelPose;
import com.github.standobyte.jojo.client.model.pose.ModelPoseTransition;
import com.github.standobyte.jojo.client.model.pose.ModelPoseTransitionMultiple;
import com.github.standobyte.jojo.client.model.pose.RotationAngle;
import com.github.standobyte.jojo.client.model.pose.anim.PosedActionAnimation;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.entity.stand.stands.StoneFreeEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class StoneFreeModel extends HumanoidStandModel<StoneFreeEntity> {
	private final ModelRenderer chest;
	private final ModelRenderer leftArmJointSlim;
	private final ModelRenderer rightArmJointSlim;
	private final ModelRenderer foreArm;
	private final ModelRenderer shortForeArm;

	public StoneFreeModel() {
		super();
		
		addHumanoidBaseBoxes(null);
		texWidth = 128;
		texHeight = 128;

		head.texOffs(0, 16).addBox(-4.5F, -4.5F, -4.05F, 9.0F, 2.0F, 4.0F, 0.1F, false);
		head.texOffs(34, 0).addBox(-2.0F, -8.5F, -4.0F, 1.0F, 6.0F, 8.0F, 0.1F, false);
		head.texOffs(34, 14).addBox(-2.6F, -8.5F, -4.0F, 1.0F, 6.0F, 8.0F, 0.1F, false);
		head.texOffs(34, 14).addBox(-2.0F, -8.5F, 3.5F, 1.0F, 6.0F, 1.0F, 0.1F, false);
		head.texOffs(34, 0).addBox(-2.6F, -8.5F, 3.5F, 1.0F, 6.0F, 1.0F, 0.1F, false);
		head.texOffs(54, 0).addBox(-3.5F, -8.5F, -4.0F, 1.0F, 6.0F, 8.0F, 0.1F, false);
		head.texOffs(54, 14).addBox(-3.5F, -8.5F, 3.5F, 1.0F, 6.0F, 1.0F, 0.1F, false);
		head.texOffs(54, 0).addBox(-4.1F, -8.5F, 3.5F, 1.0F, 6.0F, 1.0F, 0.1F, false);
		head.texOffs(54, 14).addBox(-4.1F, -8.5F, -4.0F, 1.0F, 6.0F, 8.0F, 0.1F, false);
		head.texOffs(74, 0).addBox(1.0F, -8.5F, -4.0F, 1.0F, 6.0F, 8.0F, 0.1F, false);
		head.texOffs(74, 14).addBox(1.6F, -8.5F, -4.0F, 1.0F, 6.0F, 8.0F, 0.1F, false);
		head.texOffs(74, 14).addBox(1.0F, -8.5F, 3.5F, 1.0F, 6.0F, 1.0F, 0.1F, false);
		head.texOffs(74, 0).addBox(1.6F, -8.5F, 3.5F, 1.0F, 6.0F, 1.0F, 0.1F, false);
		head.texOffs(94, 0).addBox(2.5F, -8.5F, -4.0F, 1.0F, 6.0F, 8.0F, 0.1F, false);
		head.texOffs(94, 14).addBox(3.1F, -8.5F, -4.0F, 1.0F, 6.0F, 8.0F, 0.1F, false);
		head.texOffs(94, 14).addBox(2.5F, -8.5F, 3.5F, 1.0F, 6.0F, 1.0F, 0.1F, false);
		head.texOffs(94, 0).addBox(3.1F, -8.5F, 3.5F, 1.0F, 6.0F, 1.0F, 0.1F, false);
		head.texOffs(0, 0).addBox(3.5F, -5.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 4).addBox(-4.5F, -5.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(4, 4).addBox(-5.0F, -4.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		head.texOffs(4, 0).addBox(4.0F, -4.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.1F, false);

		torso.texOffs(24, 73).addBox(-2.5F, 4.0F, -2.1F, 5.0F, 6.0F, 1.0F, 0.0F, false);

		chest = new ModelRenderer(this);
		chest.setPos(0.0F, 2.75F, -0.6F);
		torso.addChild(chest);
		setRotationAngle(chest, -0.1745F, 0.0F, 0.0F);
		chest.texOffs(20, 64).addBox(-3.5F, -1.91F, -1.5F, 7.0F, 3.0F, 1.0F, 0.4F, false);

		leftArm.texOffs(95, 81).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);
		leftArm.texOffs(105, 92).addBox(-1.5F, 2.5F, 1.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		leftArm.texOffs(106, 74).addBox(-3.0F, -3.0F, -3.0F, 5.0F, 5.0F, 6.0F, -0.75F, true);

		leftArmJointSlim = new ModelRenderer(this);
		leftArmJointSlim.setPos(-0.5F, 0.0F, 0.0F);
		leftArmJoint.addChild(leftArmJointSlim);
		leftArmJointSlim.texOffs(96, 75).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, -0.1F, true);

		leftForeArm.texOffs(95, 91).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, -0.001F, true);
		leftForeArm.texOffs(103, 74).addBox(0.5F, 5.1F, -2.0F, 1.0F, 1.0F, 1.0F, -0.2F, true);
		leftForeArm.texOffs(107, 74).addBox(0.5F, 5.1F, -1.0F, 1.0F, 1.0F, 1.0F, -0.2F, true);
		leftForeArm.texOffs(103, 76).addBox(0.5F, 5.1F, 0.0F, 1.0F, 1.0F, 1.0F, -0.2F, true);
		leftForeArm.texOffs(107, 76).addBox(0.5F, 5.1F, 1.0F, 1.0F, 1.0F, 1.0F, -0.2F, true);

		rightArm.texOffs(63, 81).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);
		rightArm.texOffs(73, 92).addBox(-0.5F, 2.5F, 1.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		rightArm.texOffs(74, 74).addBox(-2.0F, -3.0F, -3.0F, 5.0F, 5.0F, 6.0F, -0.75F, false);

		rightArmJointSlim = new ModelRenderer(this);
		rightArmJointSlim.setPos(0.5F, 0.0F, 0.0F);
		rightArmJoint.addChild(rightArmJointSlim);
		rightArmJointSlim.texOffs(64, 75).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, -0.1F, false);

		foreArm = new ModelRenderer(this);
		foreArm.setPos(6.0F, 18.0F, 0.0F);
		rightForeArm.addChild(foreArm);
		foreArm.texOffs(63, 91).addBox(-7.0F, -18.0F, -2.0F, 3.0F, 6.0F, 4.0F, -0.001F, false);
		foreArm.texOffs(71, 74).addBox(-7.5F, -12.9F, 1.0F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		foreArm.texOffs(75, 74).addBox(-7.5F, -12.9F, 0.0F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		foreArm.texOffs(71, 76).addBox(-7.5F, -12.9F, -1.0F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		foreArm.texOffs(75, 76).addBox(-7.5F, -12.9F, -2.0F, 1.0F, 1.0F, 1.0F, -0.2F, false);

		shortForeArm = new ModelRenderer(this);
		shortForeArm.setPos(6.0F, 19.0F, 7.0F);
		rightForeArm.addChild(shortForeArm);
		shortForeArm.texOffs(79, 91).addBox(-7.0F, -19.0F, -9.0F, 3.0F, 2.0F, 4.0F, -0.001F, false);

		leftLeg.texOffs(80, 119).addBox(0.1F, 4.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		leftLeg.texOffs(80, 122).addBox(-0.4F, 4.0F, -2.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		leftLeg.texOffs(76, 119).addBox(-0.9F, 4.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		rightLeg.texOffs(80, 122).addBox(-0.6F, 4.0F, -2.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		rightLeg.texOffs(80, 119).addBox(-0.1F, 4.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		rightLeg.texOffs(76, 119).addBox(-1.1F, 4.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		rightLegJoint = new ModelRenderer(this);
		rightLegJoint.setPos(0.0F, 6.0F, 0.0F);
		rightLeg.addChild(rightLegJoint);
		rightLegJoint.texOffs(64, 102).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.1F, false);
	}

	@Override
	public void prepareMobModel(StoneFreeEntity entity, float walkAnimPos, float walkAnimSpeed, float partialTick) {
		super.prepareMobModel(entity, walkAnimPos, walkAnimSpeed, partialTick);
		if (foreArm != null) {
			foreArm.visible = entity.hasForeArm();
		}
		if (shortForeArm != null) {
			shortForeArm.visible = entity.hasShortForeArm();
		}
	}

	@Override
	protected RotationAngle[][] initSummonPoseRotations() {
		return new RotationAngle[][] {
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 0, -15F, 0),
						RotationAngle.fromDegrees(body, -10F, -10F, 0),
						RotationAngle.fromDegrees(upperPart, 0, 0, 0),
						RotationAngle.fromDegrees(leftArm, 10F, 0, -2.5F),
						RotationAngle.fromDegrees(leftForeArm, -15F, 0, 2.5F),
						RotationAngle.fromDegrees(rightArm, 10F, 0, 2.5F),
						RotationAngle.fromDegrees(rightForeArm, -2.5F, 0, 2.5F),
						RotationAngle.fromDegrees(leftLeg, 7.5F, 0, -5F),
						RotationAngle.fromDegrees(leftLowerLeg, 2.5F, 0, 5F),
						RotationAngle.fromDegrees(rightLeg, 5F, 0, 5F),
						RotationAngle.fromDegrees(rightLowerLeg, 2.5F, 0, -5)
				},
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 0, 0, 0),
						RotationAngle.fromDegrees(body, 5F, -20F, 0),
						RotationAngle.fromDegrees(upperPart, 0, 0, 0),
						RotationAngle.fromDegrees(leftArm, 0, 0, 0),
						RotationAngle.fromDegrees(leftForeArm, -7.5F, 0, 0),
						RotationAngle.fromDegrees(rightArm, 0, -50F, 20F),
						RotationAngle.fromDegrees(rightForeArm, -40F, 0, 0),
						RotationAngle.fromDegrees(leftLeg, -15F, -15F, 0),
						RotationAngle.fromDegrees(leftLowerLeg, 10F, 0, 0),
						RotationAngle.fromDegrees(rightLeg, -7.5F, 15F, 0),
						RotationAngle.fromDegrees(rightLowerLeg, 2.5F, 0, 0)
				}
		};
	}

	@Override
	protected void initActionPoses() {
        actionAnim.put(StandPose.RANGED_ATTACK, new PosedActionAnimation.Builder<StoneFreeEntity>()
                .addPose(StandEntityAction.Phase.BUTTON_HOLD, new ModelPose<>(new RotationAngle[] {
                        new RotationAngle(body, 0.0F, -0.48F, 0.0F),
                        new RotationAngle(leftArm, 0.0F, 0.0F, 0.0F),
                        new RotationAngle(leftForeArm, 0.0F, 0.0F, 0.0F),
                        new RotationAngle(rightArm, -1.0908F, 0.0F, 1.5708F), 
                        new RotationAngle(rightForeArm, 0.0F, 0.0F, 0.0F)
                }))
                .build(idlePose));

		super.initActionPoses();
	}

	@Override
	protected ModelPose<StoneFreeEntity> initIdlePose() {
		return new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, -5F, 30F, 0.0F),
				RotationAngle.fromDegrees(upperPart, 0.0F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(torso, 0.0F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(leftArm, 12.5F, -30F, -15F),
				RotationAngle.fromDegrees(leftForeArm, -12.5F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(rightArm, 10F, 30F, 15F),
				RotationAngle.fromDegrees(rightForeArm, -15F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(leftLeg, 20F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(leftLowerLeg, 0.0F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(rightLeg, 0.0F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(rightLowerLeg, 5F, 0.0F, 0.0F)
		});
	}

	@Override
	protected ModelPose<StoneFreeEntity> initIdlePose2Loop() {
		return new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(leftArm, 7.5F, -30F, -15F),
				RotationAngle.fromDegrees(leftForeArm, -17.5F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(rightArm, 12.5F, 30F, 15F),
				RotationAngle.fromDegrees(rightForeArm, -17.5F, 0.0F, 0.0F)
		});
	}
}