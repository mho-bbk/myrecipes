package com.example.myrecipes.data.db

import androidx.room.*
import com.example.myrecipes.data.models.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Reference: Philip Lackner, https://www.youtube.com/watch?v=hROS736jM6A&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ&index=4
 */
@Dao
interface RecipeDao {

    // Returns ID
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe: Recipe)

    @Query("SELECT * from recipes")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("DELETE FROM recipes")
    suspend fun deleteAll()
}