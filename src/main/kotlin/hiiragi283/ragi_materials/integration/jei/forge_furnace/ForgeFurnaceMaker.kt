package hiiragi283.ragi_materials.integration.jei.forge_furnace

import com.google.common.collect.Lists
import hiiragi283.ragi_materials.block.ForgeFurnaceHelper

import mezz.jei.api.IModRegistry

object ForgeFurnaceMaker {

    fun register(registry: IModRegistry) {
        //listの宣言
        val list: MutableList<ForgeFurnaceRecipe?> = Lists.newArrayList()
        val listMap = listOf(
            ForgeFurnaceHelper.mapForgeBurning,
            ForgeFurnaceHelper.mapForgeBlasting
        )
        //listMap内の各mapに対して実行
        for (map in listMap) {
            //mapの各inputに対して実行
            for (input in map.keys) {
                //レシピを生成
                val info = ForgeFurnaceRecipe(input, map)
                //完成品が空でない場合
                if (!info.stackOut.isEmpty) {
                    //listにレシピを追加
                    list.add(info)
                }
            }
        }
        //リストをレシピ一覧に登録
        registry.addRecipes(list, "ragi_materials.forge_furnace")
    }
}