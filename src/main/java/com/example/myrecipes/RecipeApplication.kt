package com.example.myrecipes

import android.app.Application
import com.example.myrecipes.data.db.RecipeDatabase
import com.example.myrecipes.repository.RecipeRepository

class RecipeApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { RecipeDatabase.getDatabase(this) }
    val repository by lazy { RecipeRepository(database.getRecipeDao()) }
}