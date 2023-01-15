package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weather.fragment.FragmentViewModel
import com.example.weather.remote.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val fragmentViewModel: FragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}


