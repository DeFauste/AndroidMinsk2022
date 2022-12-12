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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        findViewById<TextView>(R.id.textResult).text = calculator.getNumbersToString()

        findViewById<Button>(R.id.buttonCalculated).setOnClickListener {

            val secondActivity = Intent(this, CalculationsActivity::class.java)
            startActivity(secondActivity)
        }
    }

}