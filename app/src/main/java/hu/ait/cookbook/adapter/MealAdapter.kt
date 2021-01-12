package hu.ait.cookbook.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.ait.cookbook.R
import hu.ait.cookbook.RecipeActivity
import hu.ait.cookbook.ScrollingActivity
import hu.ait.cookbook.data.AppDatabase
import hu.ait.cookbook.data.Meal
import hu.ait.cookbook.touch.MealTouchHelperCallback
import kotlinx.android.synthetic.main.item_row.view.*
import java.util.*

class MealAdapter: RecyclerView.Adapter<MealAdapter.ViewHolder>, MealTouchHelperCallback{

    var meals = mutableListOf<Meal>()

    val context: Context

    constructor(context: Context, listItems: List<Meal>){
        this.context = context
        meals.addAll(listItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_row, parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = meals[position]


        holder.tvName.text = currentItem.name
        bindDelete(holder)
        bindFind(holder)
    }

    fun bindDelete(holder: ViewHolder){
        holder.btnDelete.setOnClickListener{
            deleteItem(holder.adapterPosition)
        }
    }

    fun bindFind(holder: ViewHolder){
        holder.btnFind.setOnClickListener {
            var intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("meal", holder.tvName.text.toString())
            context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
        return meals.size
    }

    fun deleteItem(position: Int) {
        Thread{
            AppDatabase.getInstance(context).mealDao().deleteItem(
                meals.get(position))

            (context as ScrollingActivity).runOnUiThread{
                meals.removeAt(position)
                notifyItemRemoved(position)
            }
        }.start()
    }

    fun addItem(item: Meal){
        meals.add(item)
        notifyItemInserted(meals.lastIndex)
    }


    fun removeAll() {
        meals.clear()
        notifyDataSetChanged()
    }


    override fun onDismissed(position: Int) {
        deleteItem(position)
    }



    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(meals, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName = itemView.tvMeal
        val btnDelete = itemView.btnDelete
        val btnFind = itemView.btnFind
    }

}