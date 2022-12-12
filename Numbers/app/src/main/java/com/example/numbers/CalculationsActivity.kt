package com.example.numbers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.numbers.observer.Calculator
import com.example.numbers.observer.Subscriber

class CalculationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculations)
    }

    override fun onStart() {
        super.onStart()
        val calculator = Calculator
        val subscriber = Subscriber("Activity_2")
        calculator.addObserver(subscriber)

        findViewById<Button>(R.id.buttonSendResult).setOnClickListener {
            val secondActivity = Intent(this, MainActivity::class.java)
            startActivity(secondActivity)
        }
    }
}