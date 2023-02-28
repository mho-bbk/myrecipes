package com.example.myrecipes.converter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ConverterViewModelTest {

    // Need this rule, else will get a RuntimeException, android.os.Looper not mocked
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ConverterViewModel
    private val flour = 125.16
    private val sugar = 200.86
    private val butter = 226.89
    private val milk = 244.87

    @Before
    fun setUp() {
        viewModel = ConverterViewModel()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Given flour when conversion is complete the correct string is set in viewModel`() {
        val expectedIngredientQuantity = 1.00
        val expected = "${"%.2f".format(expectedIngredientQuantity)} cups to ${expectedIngredientQuantity * flour} grams"
        viewModel.convert(expectedIngredientQuantity.toString(), "flour")
        val actual = viewModel.conversionString.value
        assertEquals(expected, actual)
    }

    @Test
    fun `Given sugar when conversion is complete the correct string is set in viewModel`() {
        val expectedIngredientQuantity = 2.00
        val expected = "${"%.2f".format(expectedIngredientQuantity)} cups to ${expectedIngredientQuantity * sugar} grams"
        viewModel.convert(expectedIngredientQuantity.toString(), "sugar")
        val actual = viewModel.conversionString.value
        assertEquals(expected, actual)
    }

    @Test
    fun `Given butter when conversion is complete the correct string is set in viewModel`() {
        val expectedIngredientQuantity = 3.00
        val expected = "${"%.2f".format(expectedIngredientQuantity)} cups to ${expectedIngredientQuantity * butter} grams"
        viewModel.convert(expectedIngredientQuantity.toString(), "butter")
        val actual = viewModel.conversionString.value
        assertEquals(expected, actual)
    }

    @Test
    fun `Given milk when conversion is complete the correct string is set in viewModel`() {
        val expectedIngredientQuantity = 4.00
        val expected = "${"%.2f".format(expectedIngredientQuantity)} cups to ${expectedIngredientQuantity * milk} grams"
        viewModel.convert(expectedIngredientQuantity.toString(), "milk")
        val actual = viewModel.conversionString.value
        assertEquals(expected, actual)
    }

    @Test
    fun `Given flour convert produces correct result`() {
        val expected = 1 * flour
        viewModel.convert("1", "flour")
        val actual = viewModel.convertedIngredientQuantity.value
        assertEquals(expected, actual)
    }

    @Test
    fun `Given sugar convert produces correct result`() {
        val expected = 2 * sugar
        viewModel.convert("2", "suGaR")
        val actual = viewModel.convertedIngredientQuantity.value
        assertEquals(expected, actual)
    }

    @Test
    fun `Given butter convert produces correct result`() {
        val expected = 3 * butter
        viewModel.convert("3", "Butter")
        val actual = viewModel.convertedIngredientQuantity.value
        assertEquals(expected, actual)
    }

    @Test
    fun `Given milk convert produces correct result`() {
        val expected = 4 * milk
        viewModel.convert("4", "milk")
        val actual = viewModel.convertedIngredientQuantity.value
        assertEquals(expected, actual)
    }

    @Test
    fun `isInputValid returns false if input is String`() {
        val input = "some string"

        assertEquals(false, viewModel.isInputValid(input))
    }

    @Test
    fun `isInputValid returns false if input is zero`() {
        val input = "0"

        assertEquals(false, viewModel.isInputValid(input))
    }

    @Test
    fun `isInputValid returns true if input above zero`() {
        val input1 = "3"
        val input2 = "16"
        val input3 = "34.6"

        assertEquals(true, viewModel.isInputValid(input1))
        assertEquals(true, viewModel.isInputValid(input2))
        assertEquals(true, viewModel.isInputValid(input3))
    }
}