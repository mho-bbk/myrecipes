package com.example.myrecipes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myrecipes.data.models.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Reference: Philip Lackner, https://www.youtube.com/watch?v=hROS736jM6A&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ&index=4
 */
// Databases for Room always need to be abstract
// Versioning informs Room when we make updates
@Database(
    entities = arrayOf(Recipe::class),
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao

    private class RecipeDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.getRecipeDao())
                }
            }
        }

        suspend fun populateDatabase(recipeDao: RecipeDao) {
            // Delete all content here
            recipeDao.deleteAll()

            // Add sample recipes
            val recipe = Recipe(
                id = 0,
                recipeName = "Not real recipe",
                recipeLink = "notreal.com",
                rating = "0",
                notes = "not real, rubbish"
            )
            recipeDao.upsert(recipe)

            // TODO add own recipes
        }
    }

    companion object {
        // Volatile = other threads can see when this database is volatile
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

       fun getDatabase(
           context: Context,
           scope: CoroutineScope
       ): RecipeDatabase {
           // if the INSTANCE is not null, then return it,
           // if it is, then create the database
           return INSTANCE ?: synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   RecipeDatabase::class.java,
                   "room_db.db"
               )
                   .addCallback(RecipeDatabaseCallback(scope))
                   .build()
               INSTANCE = instance
               instance
           }
       }
    }
}