package com.example.myrecipes.recipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.adapters.RecipeAdapter
import com.example.myrecipes.databinding.FragmentRecipesSavedBinding

class SavedRecipesFragment : Fragment() {

    private val viewModel: RecipesViewModel by activityViewModels()
    lateinit var recipeAdapter: RecipeAdapter

    private lateinit var binding: FragmentRecipesSavedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter()
        binding.savedRecipes.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}