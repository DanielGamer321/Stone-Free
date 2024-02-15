package com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringSpikeEntity;
import com.github.standobyte.jojo.client.render.entity.model.ownerbound.repeating.RepeatingModel;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class SFUStringSpikeModel extends RepeatingModel<SFUStringSpikeEntity> {
	private final ModelRenderer spike;
	private final ModelRenderer string;

	public SFUStringSpikeModel() {
		texWidth = 32;
		texHeight = 32;

		spike = new ModelRenderer(this);
		spike.setPos(0.0F, 24.0F, 0.0F);
		spike.texOffs(0, 18).addBox(-2.0F, -23.5F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		spike.texOffs(6, 18).addBox(-2.0F, -23.5F, -0.8F, 2.0F, 2.0F, 1.0F, -0.2F, false);
		spike.texOffs(6, 18).addBox(-2.0F, -23.5F, -1.2F, 2.0F, 2.0F, 1.0F, -0.2F, false);
		spike.texOffs(12, 18).addBox(-1.5F, -23.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		spike.texOffs(16, 18).addBox(-1.5F, -23.0F, -2.8F, 1.0F, 1.0F, 1.0F, -0.2F, false);

		string = new ModelRenderer(this);
		string.setPos(0.0F, 24.0F, 0.0F);
		string.texOffs(0, 9).addBox(-1.5F, -23.0F, 1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	protected ModelRenderer getMainPart() {
		return spike;
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