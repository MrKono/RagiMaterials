package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack

object OreDictRegistry {

    private val listOreDict = listOf(
            OreDictHandler(EnumMaterialType.BLOCK_MATERIAL, "block", PartRegistry.BLOCK),
            OreDictHandler(EnumMaterialType.CRYSTAL, "gem", PartRegistry.CRYSTAL),
            OreDictHandler(EnumMaterialType.DUST, "dust", PartRegistry.DUST),
            OreDictHandler(EnumMaterialType.DUST, "dustTiny", PartRegistry.DUST_TINY),
            OreDictHandler(EnumMaterialType.INGOT, "ingot", PartRegistry.INGOT),
            OreDictHandler(EnumMaterialType.PLATE, "plate", PartRegistry.PLATE),
            OreDictHandler(EnumMaterialType.STICK, "stick", PartRegistry.STICK),
            OreDictHandler(EnumMaterialType.GEAR, "gear", PartRegistry.GEAR),
            OreDictHandler(EnumMaterialType.INGOT_HOT, "ingotHot", PartRegistry.INGOT_HOT),
            OreDictHandler(EnumMaterialType.NUGGET, "nugget", PartRegistry.NUGGET)
    )

    //鉱石辞書を登録するメソッド
    fun load() {
        //list内の各materialに対して実行
        for (material in RagiMaterial.list) {
            //listOreDict内の各OreDictHandlerに対して実行
            for (oredict in listOreDict) {
                val stack = MaterialUtil.getPart(oredict.part, material)
                //materialのtypeがoredictのtypeを含む場合
                if (oredict.type in material.type.list) {
                    RagiUtil.setOreDict(oredict.prefix + material.getOreDict(), stack)
                    //別の鉱石辞書名を持っている場合
                    if (material.oredictAlt !== null) {
                        RagiUtil.setOreDict(oredict.prefix + material.oredictAlt, stack)
                    }
                }
            }
        }

        //Ore
        for (i in OreProperty.listOre1.indices) {
            RagiUtil.setOreDict("ore${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.BLOCK.BlockOre1, 1, i))
            RagiUtil.setOreDict("crushed${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.ITEM.ItemOreCrushed, 1, i))
        }
        RagiUtil.setOreDict("oreSaltpeter", ItemStack(RagiRegistry.BLOCK.BlockOre1, 1, 6))
        RagiUtil.setOreDict("oreSaltpeterCrushed", ItemStack(RagiRegistry.ITEM.ItemOreCrushed, 1, 6))

        for (i in OreProperty.listVanilla.indices) {
            RagiUtil.setOreDict("crushed${OreProperty.listVanilla[i].first}", ItemStack(RagiRegistry.ITEM.ItemOreCrushedVanilla, 1, i))
        }

        //Others
        RagiUtil.setOreDict("charcoal", MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.CHARCOAL))
        RagiUtil.setOreDict("dustGunpowder", RagiUtil.getStack("minecraft:gunpowder", 1, 0))
        RagiUtil.setOreDict("dustSugar", RagiUtil.getStack("minecraft:sugar", 1, 0))
        RagiUtil.setOreDict("fuelCoke", MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.COKE))
        RagiUtil.setOreDict("gearStone", MaterialUtil.getPart(PartRegistry.GEAR, MaterialRegistry.STONE))
        RagiUtil.setOreDict("gearWood", MaterialUtil.getPart(PartRegistry.GEAR, MaterialRegistry.WOOD))
        RagiUtil.setOreDict("gemCharcoal", RagiUtil.getStack("minecraft:coal", 1, 1))
        RagiUtil.setOreDict("stickStone", MaterialUtil.getPart(PartRegistry.STICK, MaterialRegistry.STONE))
    }

    class OreDictHandler(val type: EnumMaterialType, val prefix: String, val part: MaterialPart)
}