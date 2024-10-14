package com.danielgamer321.rotp_sf.client.render.entity.layerrenderer;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.init.AddonStands;
import com.danielgamer321.rotp_sf.init.InitEffects;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.client.standskin.StandSkinsManager;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class MobiusStripLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    private static final Map<PlayerRenderer, MobiusStripLayer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>> RENDERER_LAYERS = new HashMap<>();

    public MobiusStripLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
        if (renderer instanceof PlayerRenderer) {
            RENDERER_LAYERS.put((PlayerRenderer) renderer, (MobiusStripLayer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>) this);
        }
    }
    
    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, 
            T entity, float walkAnimPos, float walkAnimSpeed, float partialTick, 
            float ticks, float headYRotation, float headXRotation) {
        if (!ClientUtil.canSeeStands()) {
            return;
        }
        if (!entity.isInvisible()) {
            M model = getParentModel();
            IStandPower.getStandPowerOptional(entity).ifPresent(stand -> {
                if (stand.getType() == AddonStands.STONE_FREE.getStandType() && entity.hasEffect(InitEffects.MOBIUS_STRIP.get())) {
                    ResourceLocation texture =  StandSkinsManager.getInstance().getRemappedResPath(manager -> manager
                            .getStandSkin(stand.getStandInstance().get()), getTexture(model, entity));
                    if (texture == null) return;

                    IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(texture));
                    model.renderToBuffer(matrixStack, vertexBuilder, packedLight, LivingRenderer.getOverlayCoords(entity, 0.0F), 1, 1, 1, 1);
                }
            });
        }
    }

    @Nullable
    private ResourceLocation getTexture(EntityModel<?> model, LivingEntity entity) {
        EffectInstance mobiusStip = entity.getEffect(InitEffects.MOBIUS_STRIP.get());
        if (mobiusStip != null) {
            return new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "textures/entity/biped/layer/mobius_strip.png");
        }
        return null;
    }
}
