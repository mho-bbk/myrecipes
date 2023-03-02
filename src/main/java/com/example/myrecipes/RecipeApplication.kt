package com.example.myrecipes

import android.app.Application
import com.example.myrecipes.data.db.RecipeDatabase
import com.example.myrecipes.repository.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RecipeApplication : Application() {
    // Populating the database isn't related to a UI lifecycle,
    // therefore don;t use a CoroutineScope like viewModelScope. It's related to the app's lifecycle.
    // Create an applicationScope, then pass that to the WordRoomDatabase.getDatabase.
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { RecipeDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { RecipeRepository(database.getRecipeDao()) }
}