package com.example.myrecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecipes.converter.ConverterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun launchConverter() {
        listIntent = Intent(this, ConverterActivity::class.java)
        startActivity(listIntent)
    }
}