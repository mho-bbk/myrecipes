package com.example.myrecipes.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myrecipes.R
import com.example.myrecipes.data.models.Recipe
import com.example.myrecipes.databinding.FragmentRecipesInputBinding

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesInputBinding

    private val viewModel: RecipesViewModel by viewModels { RecipesViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment with *data* binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipes_input, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Data binding
        binding.recipesViewModel = viewModel

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        binding.saveButton.setOnClickListener { saveRecipe() }
        binding.viewSavedButton.setOnClickListener {
            // TODO - If you wanted to save without button, call saveRecipe() here
            findNavController().navigate(R.id.action_recipesFragment_to_savedRecipesFragment)
        }
    }

    private fun saveRecipe() {
        val recipe = Recipe(
            recipeName = binding.recipeName.text.toString(),
            recipeLink = binding.recipeLink.text.toString(),
            rating = binding.recipeRating.text.toString(),
            notes = binding.recipeNotes.text.toString()
        )
        viewModel.saveRecipe(recipe)
    }
}