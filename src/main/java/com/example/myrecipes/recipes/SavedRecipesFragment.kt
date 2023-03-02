package com.example.myrecipes.recipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myrecipes.R
import com.example.myrecipes.adapters.RecipeAdapter

class SavedRecipesFragment : Fragment(R.layout.fragment_recipes_saved) {

    lateinit var viewModel: RecipesViewModel
    lateinit var adapter: RecipeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}