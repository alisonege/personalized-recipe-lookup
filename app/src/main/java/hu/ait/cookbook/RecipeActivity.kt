package hu.ait.cookbook


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import com.squareup.picasso.Picasso
import hu.ait.cookbook.data.RecipeResult
import hu.ait.cookbook.network.RecipeAPI
import kotlinx.android.synthetic.main.activity_recipe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecipeActivity : AppCompatActivity() {


    //my id: 4c937d19
    //my key: c30772038614c618715cdc45f5ea8a84

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var currentIndex = 0


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.edamam.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var etMeal = intent.getStringExtra("meal")

        val recipeAPI = retrofit.create(RecipeAPI::class.java)
        val call = recipeAPI.getRecipe(etMeal, "4c937d19","c30772038614c618715cdc45f5ea8a84")

        call.enqueue(object: Callback<RecipeResult> {
            override fun onResponse(call: Call<RecipeResult>, response: Response<RecipeResult>) {

                if(etMeal.isNullOrBlank()){
                    tvLabel.text = getString(R.string.blank_input)
                }
                else{
                    btnNext.setOnClickListener {
                        currentIndex++
                        nextHit(currentIndex, response)
                    }
                    nextHit(currentIndex, response)
                }
            }

            override fun onFailure(call: Call<RecipeResult>, t: Throwable) {
                tvLabel.text = t.message
            } })
    }


    fun nextHit(currentIndex: Int, response: Response<RecipeResult>){
        if(response.body()?.hits?.get(currentIndex) != null){
            tvLabel.text = response.body()?.hits?.get(currentIndex)?.recipe?.label
            tvIngredients.text = "Ingredients: "

            Picasso.with(this@RecipeActivity)
                .load(response.body()?.hits?.get(currentIndex)?.recipe?.image)
                .resize(700,700)
                .into(imFood)

            val myIngredients = response.body()?.hits?.get(currentIndex)?.recipe?.ingredients?.toList()
            var myText = ""

            if(myIngredients != null){
                var count = 1
                for(ingredient in myIngredients){
                    myText = myText + count + ". " + ingredient.text + System.getProperty("line.separator")
                    count++
                }
            }
            tvData.text = myText
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_zoom) {
            var zoomInAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
            recipeView.startAnimation(zoomInAnim)
        }else{
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recipe, menu)
        return true
    }


}