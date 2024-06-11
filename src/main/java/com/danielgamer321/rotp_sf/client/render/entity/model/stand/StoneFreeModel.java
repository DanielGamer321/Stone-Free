package com.danielgamer321.rotp_sf.client.render.entity.model.stand;

import javax.annotation.Nullable;

import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.client.render.entity.model.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.render.entity.pose.*;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPose.ModelAnim;
import com.github.standobyte.jojo.client.render.entity.pose.anim.PosedActionAnimation;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.util.general.MathUtil;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.8.3
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

		head.texOffs(0, 22).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.1F, false);
		head.texOffs(4, 28).addBox(-0.8F, -8.2F, -4.0F, 1.0F, 1.0F, 1.0F, 0.1F, false);
		head.texOffs(0, 16).addBox(-4.5F, -4.5F, -4.05F, 9.0F, 2.0F, 4.0F, 0.1F, false);
		head.texOffs(24, 4).addBox(-3.0F, -4.0F, -4.125F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(24, 6).addBox(1.0F, -4.0F, -4.125F, 2.0F, 1.0F, 1.0F, 0.0F, true);
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
		head.texOffs(44, 17).addBox(-3.8F, -4.8F, -4.3F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		head.texOffs(64, 17).addBox(-2.3F, -4.8F, -4.3F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		head.texOffs(84, 17).addBox(1.3F, -4.8F, -4.3F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		head.texOffs(104, 17).addBox(2.8F, -4.8F, -4.3F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		head.texOffs(0, 0).addBox(3.5F, -5.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 4).addBox(-4.5F, -5.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(4, 4).addBox(-5.0F, -4.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		head.texOffs(4, 0).addBox(4.0F, -4.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.1F, false);

		torso.texOffs(0, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.1F, false);
		torso.texOffs(24, 73).addBox(-2.5F, 4.0F, -2.1F, 5.0F, 6.0F, 1.0F, 0.0F, false);
		torso.texOffs(24, 57).addBox(-2.5F, 4.0F, -2.1F, 5.0F, 6.0F, 1.0F, 0.1F, false);

		chest = new ModelRenderer(this);
		chest.setPos(0.0F, 2.75F, -0.6F);
		torso.addChild(chest);
		setRotationAngle(chest, -0.1745F, 0.0F, 0.0F);
		chest.texOffs(20, 64).addBox(-3.5F, -1.91F, -1.5F, 7.0F, 3.0F, 1.0F, 0.4F, false);

		leftArm.texOffs(95, 81).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);
		leftArm.texOffs(111, 81).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.1F, false);
		leftArm.texOffs(105, 92).addBox(-1.5F, 2.5F, 1.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		leftArm.texOffs(106, 74).addBox(-3.0F, -3.0F, -3.0F, 5.0F, 5.0F, 6.0F, -0.75F, true);

		leftArmJointSlim = new ModelRenderer(this);
		leftArmJointSlim.setPos(-0.5F, 0.0F, 0.0F);
		leftArmJoint.addChild(leftArmJointSlim);
		leftArmJointSlim.texOffs(96, 75).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, -0.1F, true);

		leftForeArm.texOffs(95, 91).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, -0.001F, true);
		leftForeArm.texOffs(111, 91).addBox(-2.0F, -0.1F, -2.0F, 3.0F, 4.0F, 4.0F, 0.099F, true);
		leftForeArm.texOffs(103, 74).addBox(0.5F, 5.1F, -2.0F, 1.0F, 1.0F, 1.0F, -0.2F, true);
		leftForeArm.texOffs(107, 74).addBox(0.5F, 5.1F, -1.0F, 1.0F, 1.0F, 1.0F, -0.2F, true);
		leftForeArm.texOffs(103, 76).addBox(0.5F, 5.1F, 0.0F, 1.0F, 1.0F, 1.0F, -0.2F, true);
		leftForeArm.texOffs(107, 76).addBox(0.5F, 5.1F, 1.0F, 1.0F, 1.0F, 1.0F, -0.2F, true);

		rightArm.texOffs(63, 81).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);
		rightArm.texOffs(79, 81).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.1F, false);
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
		foreArm.texOffs(79, 91).addBox(-7.0F, -18.1F, -2.0F, 3.0F, 4.0F, 4.0F, 0.099F, false);
		foreArm.texOffs(71, 74).addBox(-7.5F, -12.9F, 1.0F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		foreArm.texOffs(75, 74).addBox(-7.5F, -12.9F, 0.0F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		foreArm.texOffs(71, 76).addBox(-7.5F, -12.9F, -1.0F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		foreArm.texOffs(75, 76).addBox(-7.5F, -12.9F, -2.0F, 1.0F, 1.0F, 1.0F, -0.2F, false);

		shortForeArm = new ModelRenderer(this);
		shortForeArm.setPos(6.0F, 19.0F, 7.0F);
		rightForeArm.addChild(shortForeArm);
		shortForeArm.texOffs(79, 101).addBox(-7.0F, -19.0F, -9.0F, 3.0F, 2.0F, 4.0F, -0.001F, false);

		leftLeg.texOffs(112, 108).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.1F, false);
		leftLeg.texOffs(80, 119).addBox(0.1F, 4.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		leftLeg.texOffs(112, 122).addBox(-0.4F, 4.0F, -2.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		leftLeg.texOffs(76, 119).addBox(-0.9F, 4.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		leftLowerLeg.texOffs(117, 117).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 1.0F, 0.099F, false);
		leftLowerLeg.texOffs(112, 123).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.099F, false);

		rightLeg.texOffs(80, 108).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.1F, false);
		rightLeg.texOffs(80, 122).addBox(-0.6F, 4.0F, -2.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		rightLeg.texOffs(80, 119).addBox(-0.1F, 4.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		rightLeg.texOffs(76, 119).addBox(-1.1F, 4.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		rightLowerLeg.texOffs(85, 117).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 1.0F, 0.099F, false);
		rightLowerLeg.texOffs(80, 123).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.099F, false);
	}

	@Override
	public void prepareMobModel(@Nullable StoneFreeEntity entity, float walkAnimPos, float walkAnimSpeed, float partialTick) {
		super.prepareMobModel(entity, walkAnimPos, walkAnimSpeed, partialTick);
		if (foreArm != null) {
			foreArm.visible = entity == null || entity.hasForeArm();
		}
		if (shortForeArm != null) {
			shortForeArm.visible = entity != null && entity.hasShortForeArm();
		}
	}

	@Override
	protected RotationAngle[][] initSummonPoseRotations() {
		return new RotationAngle[][] {
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 0, -15, 0),
						RotationAngle.fromDegrees(body, -10, -10, 0),
						RotationAngle.fromDegrees(upperPart, 0, 0, 0),
						RotationAngle.fromDegrees(leftArm, 10, 0, -2.5F),
						RotationAngle.fromDegrees(leftForeArm, -15, 0, 2.5F),
						RotationAngle.fromDegrees(rightArm, 10, 0, 2.5F),
						RotationAngle.fromDegrees(rightForeArm, -2.5F, 0, 2.5F),
						RotationAngle.fromDegrees(leftLeg, 7.5F, 0, -5),
						RotationAngle.fromDegrees(leftLowerLeg, 2.5F, 0, 5),
						RotationAngle.fromDegrees(rightLeg, 5, 0, 5),
						RotationAngle.fromDegrees(rightLowerLeg, 2.5F, 0, -5)
				},
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 0, 0, 0),
						RotationAngle.fromDegrees(body, 5, -20, 0),
						RotationAngle.fromDegrees(upperPart, 0, 0, 0),
						RotationAngle.fromDegrees(leftArm, 0, 0, 0),
						RotationAngle.fromDegrees(leftForeArm, -7.5F, 0, 0),
						RotationAngle.fromDegrees(rightArm, 0, -50, 20),
						RotationAngle.fromDegrees(rightForeArm, -40, 0, 0),
						RotationAngle.fromDegrees(leftLeg, -15, -15, 0),
						RotationAngle.fromDegrees(leftLowerLeg, 10, 0, 0),
						RotationAngle.fromDegrees(rightLeg, -7.5F, 15, 0),
						RotationAngle.fromDegrees(rightLowerLeg, 2.5F, 0, 0)
				},
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 0, 0, 0),
						RotationAngle.fromDegrees(body, 10, 0, 0),
						RotationAngle.fromDegrees(upperPart, 0, 0, 0),
						RotationAngle.fromDegrees(leftArm, -10, 40, -20),
						RotationAngle.fromDegrees(leftForeArm, -10, 0, 0),
						RotationAngle.fromDegrees(rightArm, -75, 0, 30),
						RotationAngle.fromDegrees(rightForeArm, -55, 0, 0),
						RotationAngle.fromDegrees(leftLeg, -10, 0, -2.5F),
						RotationAngle.fromDegrees(leftLowerLeg, 5, 0, 0),
						RotationAngle.fromDegrees(rightLeg, -10, 0, 2.5F),
						RotationAngle.fromDegrees(rightLowerLeg, 5, 0, 0)
				}
		};
	}

	@Override
	protected void initActionPoses() {
		ModelAnim<StoneFreeEntity> armsRotationFull = (rotationAmount, entity, ticks, yRotationOffset, xRotation) -> {
			float xRot = xRotation * MathUtil.DEG_TO_RAD;
			leftArm.xRotSecond = xRot;
			rightArm.xRotSecond = xRot;
		};

		ModelPose<StoneFreeEntity> heavyPunchPose1 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(head, 0, -360, 0).noDegreesWrapping(),
				RotationAngle.fromDegrees(body, 30, -290, 0),
				RotationAngle.fromDegrees(upperPart, 0, 0, 0),
				RotationAngle.fromDegrees(leftArm, 10, -20, -90),
				RotationAngle.fromDegrees(leftForeArm, -20, 0, 0),
				RotationAngle.fromDegrees(rightArm, 40, 0, 90),
				RotationAngle.fromDegrees(rightForeArm, -60, 0, 0),
				RotationAngle.fromDegrees(leftLeg, -30, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 30, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -20, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 20, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyPunchPose2 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(head, 0, -360, 0).noDegreesWrapping(),
				RotationAngle.fromDegrees(body, 0, -295, 20),
				RotationAngle.fromDegrees(upperPart, 0, 0, 0),
				RotationAngle.fromDegrees(leftArm, 0, -10, -77.5F),
				RotationAngle.fromDegrees(leftForeArm, -20, 0, 0),
				RotationAngle.fromDegrees(rightArm, 45, 12.5F, 80),
				RotationAngle.fromDegrees(rightForeArm, -70, 0, 0),
				RotationAngle.fromDegrees(leftLeg, -45, 0, 0F),
				RotationAngle.fromDegrees(leftLowerLeg, 40, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -46.66F, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 51.67F, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyPunchPose3 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(head, 0, -190, 0).noDegreesWrapping(),
				RotationAngle.fromDegrees(body, 30, -190, -5),
				RotationAngle.fromDegrees(upperPart, 0, 0, 0),
				RotationAngle.fromDegrees(leftArm, 50, -25, -67.5F),
				RotationAngle.fromDegrees(leftForeArm, -80, 0, 0),
				RotationAngle.fromDegrees(rightArm, 50, 25, 70),
				RotationAngle.fromDegrees(rightForeArm, -80, 0, 0),
				RotationAngle.fromDegrees(leftLeg, -60, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 50, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -73.33F, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 83.33F, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyPunchPose4 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(head, 0, 0, 0).noDegreesWrapping(),
				RotationAngle.fromDegrees(body, 40, -30, -5),
				RotationAngle.fromDegrees(upperPart, 0, 0, 0),
				RotationAngle.fromDegrees(leftArm, 60, -15, -67.5F),
				RotationAngle.fromDegrees(leftForeArm, -80, 0, 0),
				RotationAngle.fromDegrees(rightArm, 25, 25, 70),
				RotationAngle.fromDegrees(rightForeArm, -80, 0, 0),
				RotationAngle.fromDegrees(leftLeg, -75, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 60, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -100, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 115, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyPunchPose5 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, 50, -20, -5),
				RotationAngle.fromDegrees(upperPart, 0, 0, 0),
				RotationAngle.fromDegrees(leftArm, 60, -45, -80),
				RotationAngle.fromDegrees(leftForeArm, -135, 0, 0),
				RotationAngle.fromDegrees(rightArm, 35, 25, 70),
				RotationAngle.fromDegrees(rightForeArm, -100, 0, 0),
				RotationAngle.fromDegrees(leftLeg, -55, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -80, 0, 0.0F),
				RotationAngle.fromDegrees(rightLowerLeg, 65, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyPunchPose6 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, 50, -20, -5),
				RotationAngle.fromDegrees(upperPart, 0, 0, 0),
				RotationAngle.fromDegrees(leftArm, -100, -45, -100),
				RotationAngle.fromDegrees(leftForeArm, 0, 0, 0),
				RotationAngle.fromDegrees(rightArm, 45F, 25, 50),
				RotationAngle.fromDegrees(rightForeArm, -135, 0, 0),
				RotationAngle.fromDegrees(leftLeg, -55, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 60, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -80, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 65, 0, 0)
		});
		actionAnim.put(StandPose.HEAVY_ATTACK, new PosedActionAnimation.Builder<StoneFreeEntity>()
				.addPose(StandEntityAction.Phase.WINDUP, new ModelPoseTransitionMultiple.Builder<>(heavyPunchPose1)
						.addPose(0.2F, heavyPunchPose2)
						.addPose(0.4F, heavyPunchPose3)
						.addPose(0.6F, heavyPunchPose4)
						.addPose(0.8F, heavyPunchPose5)
						.build(heavyPunchPose6))
				.addPose(StandEntityAction.Phase.RECOVERY, new ModelPoseTransitionMultiple.Builder<>(heavyPunchPose6)
						.addPose(0.5F, heavyPunchPose6)
						.build(idlePose))
				.build(idlePose));



		ModelPose<StoneFreeEntity> heavyFinisherPose1 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(head, 0, 0, 0),
				RotationAngle.fromDegrees(body, 0, 0, 0),
				RotationAngle.fromDegrees(upperPart, 0, 10, 0),
				RotationAngle.fromDegrees(leftArm, -45, -10, -10),
				RotationAngle.fromDegrees(leftForeArm, -100, 0, 0),
				RotationAngle.fromDegrees(rightArm, -45, 10, 10),
				RotationAngle.fromDegrees(rightForeArm, -100, 0, 0),
				RotationAngle.fromDegrees(leftLeg, 20, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 0, 0, 0),
				RotationAngle.fromDegrees(rightLeg, 0, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 5, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyFinisherPose2 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, 20, 0, 0),
				RotationAngle.fromDegrees(upperPart, 0, 15, 0),
				RotationAngle.fromDegrees(leftArm, -36.95F, -1.12F, -47.93F),
				RotationAngle.fromDegrees(leftForeArm, -100, 0, 0),
				RotationAngle.fromDegrees(rightArm, 15, 20, 90),
				RotationAngle.fromDegrees(rightForeArm, -110, 20, -30),
				RotationAngle.fromDegrees(leftLeg, 0, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 20, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -20, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 25, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyFinisherPose3 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, 24, 0, 0),
				RotationAngle.fromDegrees(upperPart, 0, 40, 0),
				RotationAngle.fromDegrees(leftArm, -28.9F, 7.77F, -85.86F),
				RotationAngle.fromDegrees(leftForeArm, -100, 0, 0),
				RotationAngle.fromDegrees(rightArm, 24.7F, 24.66F, 84.45F),
				RotationAngle.fromDegrees(rightForeArm, -134.7539F, -5.2957F, -5.3185F),
				RotationAngle.fromDegrees(leftLeg, -5, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 25, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -25, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 30, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyFinisherPose4 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, 26, 0, 0),
				RotationAngle.fromDegrees(upperPart, 0, 40, 0),
				RotationAngle.fromDegrees(leftArm, -2.8073F, -4.4664F, -81.7246F),
				RotationAngle.fromDegrees(leftForeArm, -85.3453F, -14.7669F, 2.664F),
				RotationAngle.fromDegrees(rightArm, 34.4046F, 29.3158F, 78.9091F),
				RotationAngle.fromDegrees(rightForeArm, -134.88F, -2.65F, -2.66F),
				RotationAngle.fromDegrees(leftLeg, -7, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 27, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -27, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 32, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyFinisherPose5 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, 28, 0, 0),
				RotationAngle.fromDegrees(upperPart, 0, 0, 0),
				RotationAngle.fromDegrees(leftArm, -2.8073F, -4.4664F, -81.7246F),
				RotationAngle.fromDegrees(leftForeArm, -85.3453F, -14.7669F, 2.664F),
				RotationAngle.fromDegrees(rightArm, 50.6202F, 32.3282F, 88.6876F),
				RotationAngle.fromDegrees(rightForeArm, -135, 0, 0),
				RotationAngle.fromDegrees(leftLeg, -9, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 29, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -29, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 34, 0, 0)
		});
		ModelPose<StoneFreeEntity> heavyFinisherPose6 = new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, 30, 0, 0),
				RotationAngle.fromDegrees(upperPart, 0, -30, 0),
				RotationAngle.fromDegrees(leftArm, 50.6202F, -32.3282F, -88.6876F),
				RotationAngle.fromDegrees(leftForeArm, -135, 0, 0),
				RotationAngle.fromDegrees(rightArm, -99.9472F, 28.7283F, 47.3619F),
				RotationAngle.fromDegrees(rightForeArm, 0, 0, 0),
				RotationAngle.fromDegrees(leftLeg, -10, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 30, 0, 0),
				RotationAngle.fromDegrees(rightLeg, -30, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 35, 0, 0)
		});

		actionAnim.put(StandPose.HEAVY_ATTACK_FINISHER, new PosedActionAnimation.Builder<StoneFreeEntity>()
				.addPose(StandEntityAction.Phase.WINDUP, new ModelPoseTransitionMultiple.Builder<>(heavyFinisherPose1)
						.addPose(0.2F, heavyFinisherPose2)
						.addPose(0.4F, heavyFinisherPose3)
						.addPose(0.6F, heavyFinisherPose4)
						.addPose(0.8F, heavyFinisherPose5)
						.build(heavyFinisherPose6))
				.addPose(StandEntityAction.Phase.RECOVERY, new ModelPoseTransitionMultiple.Builder<>(heavyFinisherPose6)
						.addPose(0.5F, heavyFinisherPose6)
						.build(idlePose))
				.build(idlePose));


		actionAnim.putIfAbsent(StandPose.RANGED_ATTACK, new PosedActionAnimation.Builder<StoneFreeEntity>()
				.addPose(StandEntityAction.Phase.BUTTON_HOLD, new ModelPose<StoneFreeEntity>(new RotationAngle[] {
						RotationAngle.fromDegrees(body, 0, -27.5F, 0),
						RotationAngle.fromDegrees(rightArm, -62.5F, 0, 90),
						RotationAngle.fromDegrees(rightForeArm, 0, 0, 0)
				}).setAdditionalAnim((rotationAmount, entity, ticks, yRotationOffset, xRotation) -> {
					float xRot = xRotation * MathUtil.DEG_TO_RAD;
					rightArm.xRotSecond = xRot;
				})).build(idlePose));

		super.initActionPoses();
	}

	@Override
	protected ModelPose<StoneFreeEntity> initIdlePose() {
		return new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, -5, 30, 0),
				RotationAngle.fromDegrees(upperPart, 0, 0, 0),
				RotationAngle.fromDegrees(torso, 0, 0, 0),
				RotationAngle.fromDegrees(leftArm, 12.5F, -30, -15),
				RotationAngle.fromDegrees(leftForeArm, -12.5F, 0, 0),
				RotationAngle.fromDegrees(rightArm, 10, 30, 15),
				RotationAngle.fromDegrees(rightForeArm, -15, 0, 0),
				RotationAngle.fromDegrees(leftLeg, 20, 0, 0),
				RotationAngle.fromDegrees(leftLowerLeg, 0, 0, 0),
				RotationAngle.fromDegrees(rightLeg, 0, 0, 0),
				RotationAngle.fromDegrees(rightLowerLeg, 5, 0, 0)
		});
	}

	@Override
	protected ModelPose<StoneFreeEntity> initIdlePose2Loop() {
		return new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(leftArm, 15, -30, -15),
				RotationAngle.fromDegrees(leftForeArm, -15, 0, 0),
				RotationAngle.fromDegrees(rightArm, 12.5F, 30, 15),
				RotationAngle.fromDegrees(rightForeArm, -17.5F, 0, 0)
		});
	}
}