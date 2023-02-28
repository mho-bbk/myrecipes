package com.example.myrecipes.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {

    // TODO - ingredientQuantity and convertedIngredientQuantity perhaps not necessary
    //  as separate LiveData fields
    private var _ingredientQuantity = MutableLiveData(0.00)
    private val ingredientQuantity: LiveData<Double>
        get() = _ingredientQuantity

    private var _convertedIngredientQuantity = MutableLiveData(0.00)
    val convertedIngredientQuantity: LiveData<Double>
        get() = _convertedIngredientQuantity

    private var _convertedString = MutableLiveData("")
    val conversionString: LiveData<String>
        get() = _convertedString

    /**
     * Converts cups to grams
     */
    fun convert(cupString: String, ingredient: String) {
        _ingredientQuantity.value = cupString.toDoubleOrNull()

        // Value of conversion depends on ingredient selected
        val ingredientDensity = when(ingredient.uppercase()) {
            "FLOUR" -> FLOUR
            "SUGAR" -> SUGAR
            "BUTTER" -> BUTTER
            else -> MILK
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
        const val FLOUR = 125.16
        const val SUGAR = 200.86
        const val BUTTER = 226.89
        const val MILK = 244.87
    }
}

