package com.example.numbers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.numbers.observer.Calculator
import com.example.numbers.observer.Subscriber
import com.example.numbers.strategy.AverageListInt
import com.example.numbers.strategy.MixedListInt
import com.example.numbers.strategy.SumListInt
const val TAG_RESULT = "RESULT"
class CalculationsActivity : AppCompatActivity() {
    private val calculator = Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculations)
    }

    override fun onStart() {
        super.onStart()

        val subscriber = Subscriber("Activity_2")
        calculator.addObserver(subscriber)

        findViewById<TextView>(R.id.textResult).text = calculator.getNumbersToString()

    }

    override fun onResume() {
        super.onResume()

        sendResult()
    }

    private fun sendResult() {
        findViewById<Button>(R.id.buttonSendResult).setOnClickListener {
            checkedStrategy()
            val secondActivity = Intent(this, MainActivity::class.java)
            //show console result
            Log.d(TAG_RESULT, calculator.getResult())
            startActivity(secondActivity)
        }
    }

    fun checkedStrategy() {
        val checkedStrategy = findViewById<RadioGroup>(R.id.rgStrategy).checkedRadioButtonId
        val strategy = findViewById<RadioButton>(checkedStrategy)
        when (strategy.text) {
            getString(R.string.rb_sum) -> calculator.selectOperation(SumListInt())
            getString(R.string.rb_average) -> calculator.selectOperation(AverageListInt())
            getString(R.string.rb_mixed) -> calculator.selectOperation(MixedListInt())
        }
    }
}