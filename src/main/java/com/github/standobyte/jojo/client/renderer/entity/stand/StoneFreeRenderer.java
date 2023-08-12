package com.github.standobyte.jojo.client.renderer.entity.stand;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.model.entity.stand.StoneFreeModel;
import com.github.standobyte.jojo.entity.stand.stands.StoneFreeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class StoneFreeRenderer extends AbstractStandRenderer<StoneFreeEntity, StoneFreeModel> {
    
    private static final ResourceLocation TEXTURE = new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/stone_free.png");

    public StoneFreeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new StoneFreeModel(), 0);
    }

    @Override
    public ResourceLocation getTextureLocation(StoneFreeEntity entity) {
        return TEXTURE;
    }
}
