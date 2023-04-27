package ragi_materials.main.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.block.BlockContainerBase
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.core.util.RagiFacing
import ragi_materials.core.util.dropInventoryItems
import ragi_materials.main.tile.TileIndustrialLabo

class BlockIndustrialLabo : BlockContainerBase<TileIndustrialLabo>("industrial_labo", Material.IRON, TileIndustrialLabo::class.java, 3) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        defaultState = blockState.baseState.withProperty(RagiProperty.HORIZONTAL, EnumFacing.NORTH)
        setHarvestLevel("pickaxe", 3)
        soundType = SoundType.METAL
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, RagiProperty.HORIZONTAL)

    override fun getMetaFromState(state: IBlockState): Int = RagiFacing.getMeta(state)

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState = this.defaultState.withProperty(RagiProperty.HORIZONTAL, placer.horizontalFacing.opposite)

    @Deprecated("Deprecated in Java", ReplaceWith("blockState.baseState.withProperty(RagiProperty.HORIZONTAL, RagiFacing.getState(meta))", "hiiragi283.ragi_materials.util.RagiFacing", "hiiragi283.ragi_materials.util.RagiFacing"))
    override fun getStateFromMeta(meta: Int): IBlockState = blockState.baseState.withProperty(RagiProperty.HORIZONTAL, RagiFacing.getValue(meta))

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileIndustrialLabo) dropInventoryItems(world, pos, tile.inventory)
        super.breakBlock(world, pos, state)
    }

    override fun onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileIndustrialLabo) tile.front = state.getValue(RagiProperty.HORIZONTAL) //タイルエンティティに向きを保存させる
        super.onBlockPlacedBy(world, pos, state, placer, stack)
    }
}