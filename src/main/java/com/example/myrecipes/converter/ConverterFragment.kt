package com.example.myrecipes.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myrecipes.R
import com.example.myrecipes.databinding.FragmentConverterBinding


class ConverterFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentConverterBinding
    private lateinit var ingredient: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment with view binding
        binding = FragmentConverterBinding.inflate(inflater, container, false)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.ingredients,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.ingredientSpinner.adapter = adapter
        }

        binding.ingredientSpinner.onItemSelectedListener = this

        return binding.root
    }

    /**
     * Converts cups to grams and displays it on the screen
     */
    private fun convert() {
        val cupString = binding.cupsInput.text.toString()
        val cups = cupString.toDoubleOrNull()

        if (cups == null || cups == 0.0) {
            // TODO - refactor out later eg displayText() method
            binding.displayConvertedText.text = "Invalid value added. Try again."
            return
        }

        // Value of conversion depends on ingredient selected
//        val ingredientDensity = when(ingredient)
        // is (all-purpose) flour -> 1:125
        // is (caster) sugar -> 1:225
        // is butter -> 1:227
        // is milk -> 1:245
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        // NB: Item is already selected when moved to this Fragment (default first item Flour)
        if (p0 != null) {
            ingredient = p0.getItemAtPosition(p2).toString()
            Toast.makeText(context, "Item selected: " + ingredient, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(context, "You must select an ingredient", Toast.LENGTH_SHORT).show()
    }
}