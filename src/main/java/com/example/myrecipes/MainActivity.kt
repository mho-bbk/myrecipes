package com.example.myrecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecipes.converter.ConverterActivity
import com.example.myrecipes.databinding.ActivityMainBinding
import com.example.myrecipes.recipes.RecipesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.converterButton.setOnClickListener { launchConverterActivity() }
        binding.recipesButton.setOnClickListener { launchRecipesActivity() }
    }

    private fun launchConverterActivity() {
        listIntent = Intent(this, ConverterActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchRecipesActivity() {
        listIntent = Intent(this, RecipesActivity::class.java)
        startActivity(listIntent)
    }
}