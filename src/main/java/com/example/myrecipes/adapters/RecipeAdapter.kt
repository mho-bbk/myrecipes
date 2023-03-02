package com.example.myrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.R
import com.example.myrecipes.data.models.Recipe

/**
 * References:
 * Philip Lackner, https://www.youtube.com/watch?v=wGDX9zjWQzE&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ&index=6
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin#11
 */
class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
        // TODO set onItemClickListener here?
    }

    class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // Declare and initialize all of the list item UI components
        private val recipeNameItemView: TextView = itemView.findViewById(R.id.item_recipe_name)
        private val recipeLinkItemView: TextView = itemView.findViewById(R.id.item_link)
        private val recipeRatingItemView: TextView = itemView.findViewById(R.id.item_rating)
        private val recipeNotesItemView: TextView = itemView.findViewById(R.id.item_notes)

        fun bind(recipe: Recipe?) {
            recipeNameItemView.text = recipe?.recipeName
            recipeLinkItemView.text = recipe?.recipeLink
            recipeRatingItemView.text = recipe?.rating
            recipeNotesItemView.text = recipe?.notes
        }

        companion object {
            fun create(parent: ViewGroup): RecipeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_preview_recipe_saved, parent, false)
                return RecipeViewHolder(view)
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

    // Below enables each RecyclerView item to be clicked
    private var onItemClickListener: ((Recipe) -> Unit)? = null

    fun setOnItemClickListener(listener: (Recipe) -> Unit) {
        onItemClickListener = listener
    }
}