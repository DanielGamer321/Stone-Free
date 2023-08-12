package com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating;

import com.github.standobyte.jojo.client.render.entity.model.ownerbound.repeating.RepeatingModel;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.6.4


public class SFStringModel<T extends OwnerBoundProjectileEntity> extends RepeatingModel<T> {
	private final ModelRenderer string;

	public SFStringModel() {
		texWidth = 32;
		texHeight = 32;

		string = new ModelRenderer(this);
		string.setPos(0.0F, 24.0F, 0.0F);
		string.texOffs(0, 9).addBox(-1.5F, -23.0F, 1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	protected ModelRenderer getMainPart() {
		return null;
	}

	@Override
	protected float getMainPartLength() {
		return 0;
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