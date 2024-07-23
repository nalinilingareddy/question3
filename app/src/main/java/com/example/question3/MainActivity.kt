package com.example.question3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber1: EditText
    private lateinit var editTextNumber2: EditText
    private lateinit var spinnerOperations: Spinner
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber1 = findViewById(R.id.editTextNumber1)
        editTextNumber2 = findViewById(R.id.editTextNumber2)
        spinnerOperations = findViewById(R.id.spinnerOperations)
        textViewResult = findViewById(R.id.textViewResult)

        val operations = resources.getStringArray(R.array.operations)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOperations.adapter = adapter
    }

    fun performOperation(view: View) {
        val number1Str = editTextNumber1.text.toString()
        val number2Str = editTextNumber2.text.toString()

        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val number1 = number1Str.toDouble()
        val number2 = number2Str.toDouble()
        val operation = spinnerOperations.selectedItem.toString()

        try {
            val result = when (operation) {
                "Add" -> number1 + number2
                "Subtract" -> number1 - number2
                "Multiply" -> number1 * number2
                "Divide" -> {
                    if (number2 == 0.0) {
                        throw ArithmeticException("Division by zero!")
                    } else {
                        number1 / number2
                    }
                }
                else -> 0.0
            }

            textViewResult.text = "Result: $result"
        } catch (e: ArithmeticException) {
            textViewResult.text = "Error: ${e.message}"
        } catch (e: Exception) {
            textViewResult.text = "Error occurred"
        }
    }

    fun launchCurrencyConverter(view: View) {
        // Launch SecondActivity for currency conversion
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}
