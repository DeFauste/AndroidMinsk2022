package com.example.customview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.children
import com.example.customview.databinding.ActivityMainBinding
import com.example.customview.viewRect.CellView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allSetOnClickSquare()
    }

    private fun allSetOnClickSquare() {
        with(binding) {
            color1.setOnClickListener { changeOfClick(it.id) }
            color2.setOnClickListener { changeOfClick(it.id) }
            color3.setOnClickListener { changeOfClick(it.id) }
            color4.setOnClickListener { changeOfClick(it.id) }
            color5.setOnClickListener { changeOfClick(it.id) }
            color6.setOnClickListener { changeOfClick(it.id) }
            color7.setOnClickListener { changeOfClick(it.id) }
            color8.setOnClickListener { changeOfClick(it.id) }
        }
    }

    private fun changeOfClick(id: Int) {
        val allView = binding.mainConstraint.children
        println(Color.RED)

        for (v in allView) {
            try {
                if (v.id != id)
                    findViewById<CellView>(v.id).setCheck(false)
                if (v.id == id) {
                    val color = findViewById<CellView>(v.id).cellColor
                    val alpha = Color.alpha(color)
                    val red = Color.red(color)
                    val green = Color.green(color)
                    val blue = Color.blue(color)
                    binding.image.setColorFilter(Color.argb(alpha, red, green, blue))
                }
            } catch (e: java.lang.Exception) {
                println(e)
            }

        }
    }
}