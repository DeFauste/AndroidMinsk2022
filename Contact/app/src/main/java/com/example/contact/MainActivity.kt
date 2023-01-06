package com.example.contact

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.contact.fragment.FragmentViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    private val fragmentViewModel: FragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("fragmentViewModel.getContacts().value")
        fragmentViewModel.getContacts().observe(this) {
            println(fragmentViewModel.getContacts().value)
        }
    }

    override fun onStart() {
        super.onStart()
        sharedPreferences = getSharedPreferences(SHARED_CONTACT, Context.MODE_PRIVATE)
    }
}