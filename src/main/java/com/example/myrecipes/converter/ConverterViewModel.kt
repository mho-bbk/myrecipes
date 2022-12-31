package com.example.myrecipes.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class ConverterViewModel : ViewModel() {

    private var _ingredientQuantity = MutableLiveData(0.00)
    val ingredientQuantity: LiveData<Double>
        get() = _ingredientQuantity

    private var _convertedIngredientQuantity = MutableLiveData(0.00)
    val convertedIngredientQuantity: LiveData<Double>
        get() = _convertedIngredientQuantity

    private var _convertedString = MutableLiveData("")
    val conversionString: LiveData<String>
        get() = _convertedString

    /**
     * Converts cups to grams and displays it on the screen
     */
    fun convert(cupString: String, ingredient: String) {
        _ingredientQuantity.value = cupString.toDoubleOrNull()

        // Value of conversion depends on ingredient selected
        val ingredientDensity = when(ingredient) {
            "Flour" -> 125.16
            "Sugar" -> 200.86
            "Butter" -> 226.89
            else -> 244.87 //is Milk
        }

        _convertedIngredientQuantity.value = ingredientDensity * _ingredientQuantity.value!!
        _convertedString.value = String.format(x_cups_to_grams, ingredientQuantity.value, convertedIngredientQuantity.value)
    }

    fun isInputValid(inputString: String): Boolean {
        return if ((inputString.toDoubleOrNull()) == null || (inputString.toDoubleOrNull()) == 0.0) {
            _convertedString.value = invalid_input
            false
        } else {
            true
        }
    }

    companion object {
        const val x_cups_to_grams = "%.2f cups to %.2f grams"
        const val invalid_input = "Invalid value. Try again."
    }
}

