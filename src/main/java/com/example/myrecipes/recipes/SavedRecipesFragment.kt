package com.example.myrecipes.recipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.R
import com.example.myrecipes.adapters.RecipeAdapter

class SavedRecipesFragment : Fragment(R.layout.fragment_recipes_saved) {

    private val viewModel: RecipesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupRecyclerView()

        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.saved_recipes)
        val recipeAdapter = RecipeAdapter()
        recyclerView.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.allRecipes.observe(viewLifecycleOwner) { recipes ->
            recipes?.let { recipeAdapter.submitList(it) }
        }
    }
//
//    private fun setupRecyclerView() {
//        recipeAdapter = RecipeAdapter()
//        binding.savedRecipes.apply {
//            adapter = recipeAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
//    }

}