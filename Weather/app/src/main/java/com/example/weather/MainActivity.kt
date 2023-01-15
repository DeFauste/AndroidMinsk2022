package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.weather.remote.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}




//        lifecycleScope.launchWhenCreated {
//            val response = try {
//                RetrofitInstance.api.getWeather("London","ru","metric")
//            }catch (e: IOException) {
//                println( "onCreate: not internet")
//                return@launchWhenCreated
//            }catch (e: HttpException) {
//                println( "HttpException")
//                return@launchWhenCreated
//            }
//            if(response.isSuccessful && response.body() != null) {
////                findViewById<TextView>(R.id.text_main).text = response.body()?.list?.get(0)?.main?.temp.toString()
//                println(response.body().toString())
//            }
//        }