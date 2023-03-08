package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.render.model.*
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ModelRegistry {

    @SubscribeEvent
    fun modelRegistry(event: ModelRegistryEvent) {
        //アイテムのモデル登録
        RagiModel.setModel(
            RagiInit.ItemBlockBellow,
            RagiInit.ItemBlockBlazeHeater,
            RagiInit.ItemBlockForgeFurnace,
            RagiInit.ItemBlockForgeFurnaceNew,
            RagiInit.ItemBlockFullBottleStation,
            RagiInit.ItemBlockLaboratoryTable,
            RagiInit.ItemBlockOreDictConv,
            RagiInit.ItemBlockSaltPond,

            RagiInit.ItemBlazingCube,
            RagiInit.ItemForgeHammer,
            RagiInit.ItemFullBottle,
            RagiInit.ItemSeedCoal,
            RagiInit.ItemSeedLignite,
            RagiInit.ItemSeedPeat,
            RagiInit.ItemWaste
        )
        //アイテムのモデル登録 (メタデータ無視)
        //RagiModel.setModelSame()
        //ItemStackを用いたモデル登録
        ModelStack.init()
        //IBlockStateを用いたモデル登録
        ModelState.init()
        //液体ブロックのモデル登録
        ModelFluid.init()
    }
}