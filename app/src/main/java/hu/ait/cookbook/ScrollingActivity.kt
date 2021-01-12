package hu.ait.cookbook

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import hu.ait.cookbook.adapter.MealAdapter
import hu.ait.cookbook.data.AppDatabase
import hu.ait.cookbook.data.Meal
import hu.ait.cookbook.dialog.MealDialog
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), MealDialog.ItemHandler {

    lateinit var mealAdapter: MealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        Thread{
            var meals = AppDatabase.getInstance(this).mealDao().getAllItems()
            runOnUiThread {
                mealAdapter = MealAdapter(this, meals)
                recyclerTodo.adapter = mealAdapter
            }
        }.start()
    }

    fun saveItem(item: Meal){
        Thread{
            AppDatabase.getInstance(this).mealDao().insertItem(item)

            runOnUiThread{
                mealAdapter.addItem(item)
            }
        }.start()
    }

    fun deleteAll(){
        Thread {
            AppDatabase.getInstance(this@ScrollingActivity).mealDao().deleteAll()
            runOnUiThread {
                mealAdapter.removeAll()
            }
        }.start()
    }

    override fun itemCreated(item: Meal) {
        saveItem(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_add){
            MealDialog().show(supportFragmentManager, "Dialog")
        }else if(item.itemId == R.id.action_deleteAll){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }
}
