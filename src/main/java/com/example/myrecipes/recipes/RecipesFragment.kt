package com.example.myrecipes.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myrecipes.R
import com.example.myrecipes.databinding.FragmentRecipesInputBinding

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesInputBinding

    private val viewModel: RecipesViewModel by viewModels()

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
//        binding.viewSavedButton.setOnClickListener { //Navigate to saved recipes fragment using Navigation }
    }

    private fun saveRecipe() {
        viewModel.recipeName = binding.recipeName.toString()
        viewModel.recipeLink = binding.recipeLink.toString()
        viewModel.rating = binding.recipeRating.toString()
        viewModel.notes = binding.recipeNotes.toString()
        Toast.makeText(context, "Recipe saved", Toast.LENGTH_SHORT).show()
    }
}