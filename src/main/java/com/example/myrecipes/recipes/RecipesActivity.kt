package com.example.myrecipes.recipes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myrecipes.R
import com.example.myrecipes.RecipeApplication

class RecipesActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        // The below was to get shared VM across two fragments...
        val viewModelProviderFactory = RecipesViewModelProviderFactory((application as RecipeApplication).repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[RecipesViewModel::class.java]
    }
}