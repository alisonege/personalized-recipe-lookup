package hu.ait.cookbook.data

import org.json.JSONObject


data class RecipeResult(val q: String?, val from: Int?, val to: Int?, val params: List<List<String>>?,
                        val count: Int?, val more: Boolean?, val hits: List<Hit>?)

data class Hit(val recipe: Recipe?, val bookmarked: Boolean?, val bought: Boolean?)

data class Recipe(val uri: String?, val label: String?, val image: String?, val source: String?,
                  val url: String?, val yield: Int?, val calories: Float?, val totalWeight: Float?,
                  val ingredients: List<Ingredient>?, val totalNutrients: JSONObject?,
                  val dietLabels: List<String>?, val healthLabels: List<String>?)

data class Ingredient(val text: String?, val weight: Float?)
