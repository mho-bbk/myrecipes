package com.example.myrecipes.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myrecipes.data.models.Recipe

/**
 * Reference: Philip Lackner, https://www.youtube.com/watch?v=hROS736jM6A&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ&index=4
 */
@Dao
interface RecipeDao {

    // Returns ID
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe: Recipe): Int

    @Query("SELECT * from recipes")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}