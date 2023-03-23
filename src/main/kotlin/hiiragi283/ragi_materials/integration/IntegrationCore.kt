package hiiragi283.ragi_materials.integration

import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.integration.crafttweaker.CraftTweakerCore
import net.minecraftforge.fml.common.Loader

object IntegrationCore {

    val enableCT = Loader.isModLoaded("crafttweaker")
    val enableEIO = Loader.isModLoaded("enderio") && RagiConfig.integration.enableEIO
    val enableJEI = Loader.isModLoaded("jei")
    val enableMek = Loader.isModLoaded("mekanism") && RagiConfig.integration.enableMek
    val enableTE = Loader.isModLoaded("thermalexpansion") && RagiConfig.integration.enableTE
    val enableTF = Loader.isModLoaded("thermalfoundation") && RagiConfig.integration.enableTF

    fun loadPreInit() {}

    fun loadInit() {}

    fun loadPostInit() {
        PluginVanilla.loadPostInit()
        if (enableCT) CraftTweakerCore.apply()
    }
}