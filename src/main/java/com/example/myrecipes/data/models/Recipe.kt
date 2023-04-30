package com.example.myrecipes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipes"
)
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val recipeName: String,
    val recipeLink: String,
    val rating: String,
    val notes: String
)