package com.example.question3

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var currencySpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    private val conversionRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "GBP" to 0.73,
        "JPY" to 110.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        amountEditText = findViewById(R.id.amountEditText)
        currencySpinner = findViewById(R.id.currencySpinner)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)

        val currencies = arrayOf("USD", "EUR", "GBP", "JPY")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySpinner.adapter = adapter

        convertButton.setOnClickListener {
            convertCurrency()
        }
    }

    private fun convertCurrency() {
        val amountStr = amountEditText.text.toString()
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountStr.toDouble()
        val selectedCurrency = currencySpinner.selectedItem.toString()
        val conversionRate = conversionRates[selectedCurrency] ?: 1.0
        val convertedAmount = amount * conversionRate

        resultTextView.text = String.format("%.2f %s = %.2f %s", amount, "USD", convertedAmount, selectedCurrency)
    }
}
