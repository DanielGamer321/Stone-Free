package com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFExtendedPunchEntity;
import com.github.standobyte.jojo.client.render.entity.model.ownerbound.repeating.RepeatingModel;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class SFExtendedPunchModel extends RepeatingModel<SFExtendedPunchEntity> {
	private final ModelRenderer rightForeArm;
	private final ModelRenderer string;

	public SFExtendedPunchModel() {
		texWidth = 32;
		texHeight = 32;

		rightForeArm = new ModelRenderer(this);
		rightForeArm.setPos(0.0F, 24.0F, 0.0F);
		rightForeArm.texOffs(0, 0).addBox(-3.0F, -24.0F, -5.0F, 4.0F, 3.0F, 6.0F, -0.001F, false);
		rightForeArm.texOffs(10, 9).addBox(-3.0F, -24.0F, -3.0F, 4.0F, 3.0F, 4.0F, 0.099F, false);
		rightForeArm.texOffs(18, 2).addBox(-3.0F, -24.5F, -5.1F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		rightForeArm.texOffs(18, 4).addBox(-2.0F, -24.5F, -5.1F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		rightForeArm.texOffs(14, 2).addBox(-1.0F, -24.5F, -5.1F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		rightForeArm.texOffs(14, 4).addBox(0.0F, -24.5F, -5.1F, 1.0F, 1.0F, 1.0F, -0.2F, false);
		rightForeArm.texOffs(14, 0).addBox(-3.0F, -24.5F, -5.1F, 4.0F, 1.0F, 1.0F, -0.2F, false);

		string = new ModelRenderer(this);
		string.setPos(0.0F, 24.0F, 0.0F);
		string.texOffs(0, 9).addBox(-1.5F, -23.0F, 1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	protected ModelRenderer getMainPart() {
		return rightForeArm;
	}

	@Override
	protected float getMainPartLength() {
		return 2F;
	}

	@Override
	protected ModelRenderer getRepeatingPart() {
		return string;
	}

	@Override
	protected float getRepeatingPartLength() {
		return 8F;
	}
}