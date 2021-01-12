package hu.ait.cookbook.network

import hu.ait.cookbook.data.RecipeResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeAPI {



    @GET("/search")
    fun getRecipe(@Query("q") meal: String,
                  @Query("app_id") app_id: String,
                  @Query("app_key") app_key: String): Call<RecipeResult>

}