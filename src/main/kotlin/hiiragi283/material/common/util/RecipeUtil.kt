@file:JvmName("FileUtil")

package hiiragi283.material.common.util

import net.devtech.arrp.json.recipe.JIngredient
import net.devtech.arrp.json.recipe.JIngredients
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern

//    Pattern    //

fun get3x3(key: Char): JPattern = JPattern.pattern("$key$key$key", "$key$key$key", "$key$key$key")

//    Key    //

fun JKeys.addItem(key: String, item: String): JKeys = also {
    it.key(key, JIngredient.ingredient().item(item))
}

fun JKeys.addTag(key: String, tag: String): JKeys = also {
    it.key(key, JIngredient.ingredient().tag(tag))
}

//    Ingredient    //

fun JIngredients.addTag(tag: String) = also {
    it.add(JIngredient.ingredient().tag(tag))
}