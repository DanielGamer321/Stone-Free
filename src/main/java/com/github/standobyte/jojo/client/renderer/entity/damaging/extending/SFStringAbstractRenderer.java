package com.github.standobyte.jojo.client.renderer.entity.damaging.extending;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.client.model.entity.ownerbound.repeating.SFStringModel;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public abstract class SFStringAbstractRenderer<T extends OwnerBoundProjectileEntity> extends ExtendingEntityRenderer<T, SFStringModel<T>> {
    public SFStringAbstractRenderer(EntityRendererManager renderManager, SFStringModel<T> model) {
        super(renderManager, model, new ResourceLocation(JojoMod.MOD_ID, "textures/entity/projectiles/sf_extended_punch.png"));
    }
    
    @Override
    protected void doRender(T entity, SFStringModel<T> model, float partialTick, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        super.doRender(entity, model, partialTick, matrixStack, buffer, packedLight);
    }
}
