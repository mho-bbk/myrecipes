package com.example.myrecipes.recipes

import androidx.lifecycle.*
import com.example.myrecipes.data.models.Recipe
import com.example.myrecipes.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allRecipes: LiveData<List<Recipe>> = recipeRepository.allRecipes.asLiveData()

    // Don't need suspend as using viewModelScope
    //  Launching a new coroutine to insert the data in a non-blocking way
    fun saveRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeRepository.upsert(recipe)
    }
}

class RecipesViewModelProviderFactory(
    private val recipeRepository: RecipeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipesViewModel(recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}