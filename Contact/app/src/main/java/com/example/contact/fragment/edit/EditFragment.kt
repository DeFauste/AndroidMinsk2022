package com.example.contact.fragment.edit

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.contact.*
import com.example.contact.databinding.FragmentEditBinding
import com.example.contact.fragment.list.ListFragment
import java.util.regex.Matcher
import java.util.regex.Pattern


class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactUpdate()
        val fragmentManager = parentFragmentManager

        binding.backButton.setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view,
                    ListFragment())
                .addToBackStack(null).commit()
        }

        binding.btnDeleteContact.setOnClickListener {
            alertDialog()
        }

        saveContact()
    }
    private fun alertDialog() {
        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_CONTACT, Context.MODE_PRIVATE)

        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.alertDialog)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        sharedPreferences.edit().clear().apply()
                        val fragmentManager = parentFragmentManager
                        fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container_view,
                                ListFragment())
                            .addToBackStack(null).commit()
                    })
                setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun contactUpdate() {
        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_CONTACT, Context.MODE_PRIVATE)
        val name = sharedPreferences.getString(CONTACT_NAME, null)
        if (name != null) {
//            binding.oneContact.root.visibility = View.VISIBLE
            binding.editName.setText(name)
            val phoneEmail = sharedPreferences.getString(CONTACT_PHONE_EMAIL, null)
            if (phoneEmail != null)
                binding.editPhoneEmail.setText(phoneEmail)
            when (sharedPreferences.getInt(CONTACT_CHECKED_RB, 0)) {
                R.id.rbEmail -> binding.textTitlePhoneEmail.text = getString(R.string.email_number)
                else -> binding.textTitlePhoneEmail.text = getString(R.string.phone_number)
            }
        }
    }

    private fun saveContact() {
        binding.saveButton.setOnClickListener {
            val name = binding.editName.text.toString()
            val phoneEmail = binding.editPhoneEmail.text.toString()
            val sharedPreferences =
                requireActivity().getSharedPreferences(SHARED_CONTACT, Context.MODE_PRIVATE)

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.enter_name), Toast.LENGTH_SHORT)
                    .show()
            } else {

                if (phoneEmail.isEmpty() || (phoneEmail.isNotEmpty() && (checkEmailPhone(phoneEmail,
                        sharedPreferences.getInt(CONTACT_CHECKED_RB, 0))))
                ) {
                    sharedPreferences.edit {
                        putString(CONTACT_NAME, name)
                        putString(CONTACT_PHONE_EMAIL, phoneEmail)
                        putInt(CONTACT_CHECKED_RB, sharedPreferences.getInt(CONTACT_CHECKED_RB, 0))
                        apply()
                    }
                    val fragmentManager = parentFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view,
                            ListFragment())
                        .addToBackStack(null).commit()
                }
            }
        }
    }

    private fun checkEmailPhone(phoneEmail: String, idRb: Int): Boolean =
        when (idRb) {
            R.id.rbEmail -> checkEditEmail(phoneEmail).apply {
                Toast.makeText(requireContext(),
                    getString(R.string.toast_inc_email),
                    Toast.LENGTH_SHORT).show()
            }
            else -> checkEditNumber(phoneEmail).apply {
                Toast.makeText(requireContext(),
                    getString(R.string.toast_inc_phone),
                    Toast.LENGTH_SHORT).show()
            }
        }

    private fun checkEditNumber(phone: String): Boolean {
        var check = false
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            check = !(phone.length < 6 || phone.length > 13)
        } else {
            check = false
        }
        return check
    }

    private fun checkEditEmail(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}