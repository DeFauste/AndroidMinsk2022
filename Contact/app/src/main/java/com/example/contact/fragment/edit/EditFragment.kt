package com.example.contact.fragment.edit

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.contact.*
import com.example.contact.databinding.FragmentEditBinding
import com.example.contact.fragment.FragmentViewModel
import com.example.contact.fragment.add.checkEditEmail
import com.example.contact.fragment.add.checkEditNumber
import com.example.contact.fragment.data.Contact
import com.example.contact.fragment.list.ListFragment
import java.util.regex.Matcher
import java.util.regex.Pattern


class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: FragmentViewModel by activityViewModels()

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

        //delete contact
        binding.btnDeleteContact.setOnClickListener {
            alertDialog()
        }
        //return to the sheet contacts
        binding.backButton.setOnClickListener {
            fragmentTransaction(ListFragment(), R.id.fragment_container_view)
        }
        //save new contact
        binding.saveButton.setOnClickListener {
            saveContact()
        }
    }

    private fun fragmentTransaction(f: Fragment, idLayout: Int) {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
            .replace(idLayout, f)
            .addToBackStack(null).commit()
    }

    private fun alertDialog() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.alertDialog)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        fragmentViewModel.deleteContact(fragmentViewModel.clickItem)
                        fragmentTransaction(ListFragment(), R.id.fragment_container_view)
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
        val contact: Contact = fragmentViewModel.clickItem

        val name = contact.contactName
        binding.editName.setText(name)

        val phoneEmail = contact.contactPhoneEmail
        if (phoneEmail.isNotEmpty())
            binding.editPhoneEmail.setText(phoneEmail)

        when (contact.typeContact) {
            R.id.rbEmail -> binding.textTitlePhoneEmail.text =
                getString(R.string.email_number).apply {
                    binding.editPhoneEmail.setHint(getString(R.string.edit_mask_email))
                    binding.editPhoneEmail.inputType = InputType.TYPE_CLASS_TEXT
                }
            else -> binding.textTitlePhoneEmail.text = getString(R.string.phone_number).apply {
                binding.editPhoneEmail.setHint(getString(R.string.edit_mask_phone))
                binding.editPhoneEmail.inputType = InputType.TYPE_CLASS_PHONE
            }
        }
    }

    private fun saveContact() {
        val name = binding.editName.text.toString()
        val phoneEmail = binding.editPhoneEmail.text.toString()
        val typeContact = fragmentViewModel.clickItem.typeContact
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.enter_name), Toast.LENGTH_SHORT)
                .show()
        } else {

            if (phoneEmail.isEmpty() || (phoneEmail.isNotEmpty() && (checkEmailPhone(phoneEmail,
                    typeContact)))
            ) {
                fragmentViewModel.addContact(Contact(name, phoneEmail, typeContact))
                fragmentViewModel.deleteContact(fragmentViewModel.clickItem)
                fragmentTransaction(ListFragment(), R.id.fragment_container_view)
            }
        }
    }

    private fun checkEmailPhone(phoneEmail: String, idRb: Int): Boolean =
        when (idRb) {
            R.id.rbEmail -> phoneEmail.checkEditEmail().apply {
                if (!this) Toast.makeText(requireContext(),
                    getString(R.string.toast_inc_email),
                    Toast.LENGTH_SHORT).show()
            }
            else -> phoneEmail.checkEditNumber().apply {
                if (!this) Toast.makeText(requireContext(),
                    getString(R.string.toast_inc_phone),
                    Toast.LENGTH_SHORT).show()
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}