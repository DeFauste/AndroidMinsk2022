package com.example.contact

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.contact.converter.ConverterSharedPreferences
import com.example.contact.fragment.FragmentViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: ConverterSharedPreferences
    private val fragmentViewModel: FragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDataSharedPreferences()
    }
    fun loadDataSharedPreferences() {
        sharedPreferences = ConverterSharedPreferences(this)
        val listContacts = sharedPreferences.loadArrayList()
        fragmentViewModel.addAllContacts(listContacts)
    }

    override fun onStop() {
        fragmentViewModel.getContacts().value?.let { sharedPreferences.saveArrayList(it) }
        super.onStop()
    }
}