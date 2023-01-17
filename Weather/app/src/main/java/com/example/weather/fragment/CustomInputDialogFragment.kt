package com.example.weather.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.weather.R
import com.example.weather.database.City
import com.example.weather.databinding.PartCityInputBinding


class CustomInputDialogFragment : DialogFragment() {

    private val fragmentViewModel: FragmentViewModel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBinding = PartCityInputBinding.inflate(layoutInflater)

        val dialogFragment = AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.dialog_title))
            .setView(dialogBinding.root)
            .setPositiveButton(getString(R.string.action_dialog_positive)) { _, _ ->
                val cityName = dialogBinding.nameInputEditText.text.toString()
                addCity(cityName)
            }
            .setNegativeButton(getString(R.string.action_dialog_negative)) { _, _ -> }
            .create()

        return dialogFragment
    }


    private fun addCity(cityName: String) {
        val city = City(0, cityName, true)
        fragmentViewModel.addCity(city)
    }

    companion object {
        const val TAG = "CustomInputDialogFragment"
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}