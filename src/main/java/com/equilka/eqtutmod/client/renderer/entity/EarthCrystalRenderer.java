package com.equilka.eqtutmod.client.renderer.entity;

import com.equilka.eqtutmod.client.model.EarthCrystalModel;
import com.equilka.eqtutmod.init.ModModelLayers;
import com.equilka.eqtutmod.world.entity.EarthCrystalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EarthCrystalRenderer extends EntityRenderer<EarthCrystalEntity> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("eqtutmod", "textures/entity/earth_crystal.png");
    private final EarthCrystalModel model;

    public EarthCrystalRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new EarthCrystalModel(
                pContext.bakeLayer(ModModelLayers.EARTH_CRYSTAL)
        );
    }

    @Override
    public void render(EarthCrystalEntity pEntity, float pEntityYaw, float pPartialTick,
                       PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0D, -1.0D, 0.0D);

        VertexConsumer buffer = pBuffer.getBuffer(RenderType.entityCutout(getTextureLocation(pEntity)));

        model.setupAnim(pEntity, 0.0F, 0.0F, pEntity.tickCount + pPartialTick, 0.0F, 0.0F);
        model.renderToBuffer(pPoseStack, buffer, pPackedLight, OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F);

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EarthCrystalEntity entity) {
        return TEXTURE;
    }
}
