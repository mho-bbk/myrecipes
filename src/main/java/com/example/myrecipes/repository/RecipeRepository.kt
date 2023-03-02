package com.example.myrecipes.repository

import androidx.annotation.WorkerThread
import com.example.myrecipes.data.db.RecipeDao
import com.example.myrecipes.data.models.Recipe
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class RecipeRepository(
    private val recipeDao: RecipeDao
) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allRecipes: Flow<List<Recipe>> = recipeDao.getAllRecipes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun upsert(recipe: Recipe) = recipeDao.upsert(recipe)

    suspend fun deleteRecipe(recipe: Recipe) = recipeDao.deleteRecipe(recipe)
}