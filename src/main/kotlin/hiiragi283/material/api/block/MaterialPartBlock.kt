package hiiragi283.material.api.block

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Material
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.BlockView

data class MaterialPartBlock(
    val materialH: HiiragiMaterial,
    val part: HiiragiPart
) : HiiragiBlock(FabricBlockSettings.of(Material.METAL)) {

    override val identifier: Identifier = part.getId(materialH)
    private val tag: Identifier = part.getTag(materialH)

    override fun appendTooltip(
        stack: ItemStack,
        world: BlockView?,
        tooltip: MutableList<Text>,
        options: TooltipContext?
    ) {
        materialH.appendTooltip(tooltip, part)
    }

    //    HiiragiBlock    //

    override fun registerModel() = RagiResourcePack.addBlockState(identifier, part.state)

    override fun registerRecipe(): Unit =
        part.recipes(materialH).forEach(RagiResourcePack::addRecipe)


    override fun registerTag() {
        RagiResourcePack.addBlockTag(tag, JTag().add(identifier))
        RagiResourcePack.addItemTag(tag, JTag().add(identifier))
    }

}