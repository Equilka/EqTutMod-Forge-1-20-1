package com.equilka.eqtutmod.client.model;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.equilka.eqtutmod.init.ModModelLayers;
import com.equilka.eqtutmod.world.entity.EarthCrystalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class EarthCrystalModel extends HierarchicalModel<EarthCrystalEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = ModModelLayers.EARTH_CRYSTAL;
    private final ModelPart root;
    private final ModelPart crystal;
    private final ModelPart chunks1;
    private final ModelPart bigchunk;
    private final ModelPart chunk;
    private final ModelPart chunk2;
    private final ModelPart chunk3;
    private final ModelPart chunks2;
    private final ModelPart bigchunk3;
    private final ModelPart chunk4;
    private final ModelPart bigchunk2;

    public EarthCrystalModel(ModelPart rRoot) {
        this.root = rRoot;
        this.crystal = root.getChild("crystal");
        this.chunks1 = root.getChild("chunks1");
        this.bigchunk = this.chunks1.getChild("bigchunk");
        this.chunk = this.chunks1.getChild("chunk");
        this.chunk2 = this.chunks1.getChild("chunk2");
        this.chunk3 = this.chunks1.getChild("chunk3");
        this.chunks2 = root.getChild("chunks2");
        this.bigchunk3 = this.chunks2.getChild("bigchunk3");
        this.chunk4 = this.chunks2.getChild("chunk4");
        this.bigchunk2 = this.chunks2.getChild("bigchunk2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition crystal = partdefinition.addOrReplaceChild("crystal", CubeListBuilder.create().texOffs(18, 0).addBox(0.1F, -3.0F, -2.2F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 8).addBox(0.25F, -2.5F, -2.1F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 17.0F, 1.0F));

        PartDefinition cube_r1 = crystal.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 11).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.5F, 0.0F, 3.1321F, -0.0426F, -2.9232F));

        PartDefinition cube_r2 = crystal.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -1.9F, -0.8F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, -0.1F, 0.25F, 1.1961F, -0.2291F, -0.2182F));

        PartDefinition cube_r3 = crystal.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, -3.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, -0.1F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r4 = crystal.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(8, 11).addBox(-0.9F, -2.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -1.4232F, 0.0934F, -0.5603F));

        PartDefinition cube_r5 = crystal.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(10, 0).addBox(0.0F, -3.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition chunks1 = partdefinition.addOrReplaceChild("chunks1", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bigchunk = chunks1.addOrReplaceChild("bigchunk", CubeListBuilder.create(), PartPose.offset(1.0F, -7.0F, 5.0F));

        PartDefinition cube_r6 = bigchunk.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(25, 11).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, -0.48F));

        PartDefinition chunk = chunks1.addOrReplaceChild("chunk", CubeListBuilder.create().texOffs(3, 8).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -7.0F, -3.0F));

        PartDefinition chunk2 = chunks1.addOrReplaceChild("chunk2", CubeListBuilder.create().texOffs(10, 13).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -7.0F, 3.0F));

        PartDefinition chunk3 = chunks1.addOrReplaceChild("chunk3", CubeListBuilder.create().texOffs(2, 10).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -6.0F, 1.0F));

        PartDefinition chunks2 = partdefinition.addOrReplaceChild("chunks2", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bigchunk3 = chunks2.addOrReplaceChild("bigchunk3", CubeListBuilder.create(), PartPose.offset(4.0F, -7.0F, -3.0F));

        PartDefinition cube_r7 = bigchunk3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(14, 2).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.4505F, 0.9605F, 0.4569F));

        PartDefinition chunk4 = chunks2.addOrReplaceChild("chunk4", CubeListBuilder.create().texOffs(22, 4).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, 4.0F));

        PartDefinition bigchunk2 = chunks2.addOrReplaceChild("bigchunk2", CubeListBuilder.create(), PartPose.offset(-3.0F, -7.0F, 0.0F));

        PartDefinition cube_r8 = bigchunk2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(2, 13).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        crystal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        chunks1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        chunks2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(EarthCrystalEntity earthCrystal, float v, float v1, float ageInTicks, float v3, float v4) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(earthCrystal.activeAnimation, EarthCrystalAnimations.ACTIVE, ageInTicks, 1);
        this.animate(earthCrystal.unactiveAnimation, EarthCrystalAnimations.UNACTIVE, ageInTicks, 1);
    }
}