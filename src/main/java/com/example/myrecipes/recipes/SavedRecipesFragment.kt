package com.example.myrecipes.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.R
import com.example.myrecipes.adapters.RecipeAdapter
import com.example.myrecipes.databinding.FragmentRecipesSavedBinding

class SavedRecipesFragment : Fragment(R.layout.fragment_recipes_saved) {

    private val viewModel: SavedRecipesViewModel by viewModels { SavedRecipesViewModel.Factory }

    private lateinit var binding: FragmentRecipesSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment with *data* binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipes_saved, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeAdapter = RecipeAdapter()
        setupRecyclerView(recipeAdapter)

        viewModel.allRecipes.observe(viewLifecycleOwner) { recipes ->
            recipes?.let { recipeAdapter.submitList(it) }
        }
    }

    private fun setupRecyclerView(recipeAdapter: RecipeAdapter) {
        binding.savedRecipes.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}