package com.equilka.eqtutmod.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MojBlock extends Block {

    public MojBlock(){
        super(BlockBehaviour.Properties.copy(Blocks.BAMBOO_BLOCK));
    }

}
