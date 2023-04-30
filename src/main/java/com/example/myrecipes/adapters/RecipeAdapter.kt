package com.example.myrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.data.models.Recipe
import com.example.myrecipes.databinding.ItemPreviewRecipeSavedBinding

/**
 * References:
 * - Philip Lackner, https://www.youtube.com/watch?v=wGDX9zjWQzE&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ&index=6
 * - https://developer.android.com/codelabs/android-room-with-a-view-kotlin#11
 * - https://developer.android.com/codelabs/kotlin-android-training-interacting-with-items#3
 */
class RecipeAdapter(private val deleteButtonListener: RecipeDeleteButtonListener)
    : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe, deleteButtonListener)
    }

    class RecipeViewHolder private constructor(val binding: ItemPreviewRecipeSavedBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe?, deleteButtonListener: RecipeDeleteButtonListener) {
            binding.itemRecipeName.text = recipe?.recipeName
            binding.itemLink.text = recipe?.recipeLink
            binding.itemRating.text = recipe?.rating
            binding.itemNotes.text = recipe?.notes
            binding.deleteRecipeButton.setOnClickListener { deleteButtonListener.clickListener.invoke(recipe) }
        }

        companion object {
            fun create(parent: ViewGroup): RecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPreviewRecipeSavedBinding.inflate(layoutInflater, parent, false)
                return RecipeViewHolder(binding)
            }
        }
    }

    // TODO - learning: look up DiffUtil
    // Else the below is boilerplate
    class RecipeComparator: DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}

class RecipeDeleteButtonListener(val clickListener: (recipe: Recipe?) -> Unit)