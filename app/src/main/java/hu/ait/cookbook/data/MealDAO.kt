package hu.ait.cookbook.data

import androidx.room.*

@Dao
interface MealDAO {
    @Query("SELECT * FROM meal")
    fun getAllItems(): List<Meal>

    @Insert
    fun insertItem(item: Meal)

    @Delete
    fun deleteItem(item: Meal)

    @Query("DELETE FROM [meal]")
    fun deleteAll()

}