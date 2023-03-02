package com.example.myrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.R
import com.example.myrecipes.data.models.Recipe

/**
 * Reference: Philip Lackner, https://www.youtube.com/watch?v=wGDX9zjWQzE&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ&index=6
 */
class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // Declare and initialize all of the list item UI components
        val recipeNameTextView: TextView = itemView.findViewById(R.id.item_recipe_name)
        val recipeLinkTextView: TextView = itemView.findViewById(R.id.recipe_link)
        val recipeRatingTextView: TextView = itemView.findViewById(R.id.recipe_rating)
        val recipeNotesTextView: TextView = itemView.findViewById(R.id.recipe_notes)
    }

    // TODO - learning: look up DiffUtil
    private val differCallback = object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    // Manages the list
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_preview_recipe_saved,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        holder.apply {
            recipeNameTextView.text = recipe.recipeName
            recipeLinkTextView.text = recipe.recipeLink
            recipeRatingTextView.text = recipe.rating
            recipeNotesTextView.text = recipe.notes
            itemView.setOnClickListener {
                onItemClickListener?.let { it(recipe) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // Below enables each RecyclerView item to be clicked
    private var onItemClickListener: ((Recipe) -> Unit)? = null

    fun setOnItemClickListener(listener: (Recipe) -> Unit) {
        onItemClickListener = listener
    }
}