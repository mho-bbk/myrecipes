package com.example.myrecipes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myrecipes.data.models.Recipe

/**
 * Reference: Philip Lackner, https://www.youtube.com/watch?v=hROS736jM6A&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ&index=4
 */
// Databases for Room always need to be abstract
// Versioning informs Room when we make updates
@Database(
    entities = [Recipe::class],
    version = 1
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao

    companion object {
        // Volatile = other threads can see when this database is volatile
        @Volatile
        private var instance: RecipeDatabase? = null

        // Use LOCK to ensure only ever one DB
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // Anything here cannot be accessed by other threads
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            "recipes_db.db"
        ).build()
    }
}