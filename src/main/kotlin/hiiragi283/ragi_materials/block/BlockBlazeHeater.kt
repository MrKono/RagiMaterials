package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockBlazeHeater : Block(Material.ROCK) {

    //private変数の宣言
    companion object {
        val propertyFacing: PropertyDirection = BlockHorizontal.FACING
        val propertyHell: PropertyBool = PropertyBool.create("hellrise")
    }

    private val registryName = "blaze_heater"

    //コンストラクタの初期化
    init {
        defaultState =
            blockState.baseState.withProperty(propertyFacing, EnumFacing.NORTH).withProperty(propertyHell, false)
        setCreativeTab(CreativeTabs.DECORATIONS)
        setHardness(3.5F)
        setHarvestLevel("pickaxe", 0)
        setRegistryName(Reference.MOD_ID, registryName)
        setResistance(3.5F)
        soundType = SoundType.STONE
        unlocalizedName = registryName
    }

    //Blockstateの登録をするメソッド
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, propertyFacing, propertyHell)
    }

    //Blockstateからメタデータを得るメソッド
    override fun getMetaFromState(state: IBlockState): Int {
        return when (state.getValue(propertyFacing)) {
            EnumFacing.NORTH -> if (!state.getValue(propertyHell)) 0 else 4
            EnumFacing.SOUTH -> if (!state.getValue(propertyHell)) 1 else 5
            EnumFacing.WEST -> if (!state.getValue(propertyHell)) 2 else 6
            EnumFacing.EAST -> if (!state.getValue(propertyHell)) 3 else 7
            else -> 0
        }
    }

    //メタデータからBlockstateを得るメソッド
    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState {
        val facing = EnumFacing.getFront((meta / 4) + 2)
        val hell = meta % 4
        return blockState.baseState.withProperty(propertyFacing, facing).withProperty(
            propertyHell, hell == 1
        )
    }

    //ブロックが設置されたときに呼び出されるメソッド
    override fun getStateForPlacement(
        world: World,
        pos: BlockPos,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float,
        meta: Int,
        placer: EntityLivingBase,
        hand: EnumHand
    ): IBlockState {
        val stack = placer.getHeldItem(hand)
        var isHellrise = false
        if (stack.metadata == 1) isHellrise = true
        return this.defaultState.withProperty(propertyFacing, placer.horizontalFacing.opposite)
            .withProperty(propertyHell, isHellrise)
    }

    //ブロックを右クリックした時に呼ばれるメソッド
    override fun onBlockActivated(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): Boolean {
        //プレイヤーが利き手に持っているアイテムを取得
        val stack = player.getHeldItem(hand)
        if (state.getValue(propertyHell)) {
            ForgeFurnaceHelper.getResult(world, pos, state, stack, ForgeFurnaceHelper.mapForgeHellrise) //レシピ実行
        } else {
            ForgeFurnaceHelper.getResult(world, pos, state, stack, ForgeFurnaceHelper.mapForgeBoosted) //レシピ実行
        }
        return true
    }

    //Blockstateをもとにドロップするアイテムのメタデータを指定するメソッド
    override fun damageDropped(state: IBlockState): Int {
        //ヘルライズしているかどうかで区別
        return if (state.getValue(propertyHell)) 1 else 0
    }

    //ドロップするアイテムを得るメソッド
    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item {
        //Blockstateからブロックを取得し、更にそこからアイテムを取得して返す
        return Item.getItemFromBlock(state.block)
    }

    //ドロップする確率を得るメソッド
    override fun quantityDropped(random: Random): Int {
        //常にドロップさせるので1を返す
        return 1
    }

    //ブロックがフルブロックかどうかを判定するメソッド
    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    //ブロックが光を透過するかを判定するメソッド
    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }
}