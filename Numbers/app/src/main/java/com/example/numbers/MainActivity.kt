package com.example.numbers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.numbers.observer.Calculator
import com.example.numbers.observer.Subscriber

class MainActivity : AppCompatActivity() {
    private val calculator = Calculator
    lateinit var textNumbers: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        textNumbers = findViewById(R.id.textNumbers)
        updateTextView()
    }

    private fun updateTextView() {
        textNumbers.text = calculator.getNumbersToString()
    }

    override fun onResume() {
        super.onResume()

        generateNumbers()

        calculateResult()
    }

    private fun generateNumbers() {
        findViewById<Button>(R.id.buttonGenerate).setOnClickListener {
            calculator.updateNumbers()
            updateTextView()
        }

        findViewById<TextView>(R.id.textResult).text = calculator.getResult()
    }

    private fun calculateResult() {
        findViewById<Button>(R.id.buttonCalculated).setOnClickListener {
            val secondActivity = Intent(this, CalculationsActivity::class.java)
            startActivity(secondActivity)
        }
    }

}