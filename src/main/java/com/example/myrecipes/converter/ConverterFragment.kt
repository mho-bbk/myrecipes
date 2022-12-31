package com.example.myrecipes.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.myrecipes.R
import com.example.myrecipes.databinding.FragmentConverterBinding

class ConverterFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentConverterBinding
    private lateinit var ingredient: String

    private val viewModel: ConverterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment with *data* binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_converter, container, false)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Data binding
        binding.converterViewModel = viewModel

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        // Set up click listener for convert button
        binding.conversionButton.setOnClickListener { convert() }
    }

    /**
     * Converts cups to grams and displays it on the screen
     */
    private fun convert() {
        val cupString = binding.cupsInput.text.toString()
        if(viewModel.isInputValid(cupString)) {
            viewModel.convert(cupString, ingredient)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        // NB: Item is already selected when moved to this Fragment (default first item Flour)
        if (p0 != null) {
            ingredient = p0.getItemAtPosition(p2).toString()
//            Toast.makeText(context, "Item selected: " + ingredient, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(context, "You must select an ingredient", Toast.LENGTH_SHORT).show()
    }
}