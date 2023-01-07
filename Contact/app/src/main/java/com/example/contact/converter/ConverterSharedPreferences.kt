package com.example.contact.converter

import android.content.Context

import com.example.contact.CONTACT_LIST
import com.example.contact.SHARED_CONTACT
import com.example.contact.fragment.data.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ConverterSharedPreferences(val context: Context) {
    fun saveArrayList(arrayList: ArrayList<Contact>) {
        val sharedPreferences = context.getSharedPreferences(SHARED_CONTACT, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(arrayList)
        editor.putString(CONTACT_LIST, json)
        editor.apply()
    }

    fun loadArrayList(): ArrayList<Contact> {
        val sharedPreferences = context.getSharedPreferences(SHARED_CONTACT, Context.MODE_PRIVATE)
        val json: String? = sharedPreferences.getString(CONTACT_LIST, null)
        val turnsType = object : TypeToken<ArrayList<Contact>>() {}.type
        return Gson().fromJson(json, turnsType) ?: arrayListOf()
    }
}