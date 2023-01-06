package com.example.contact.fragment.add

import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.contact.*
import com.example.contact.databinding.FragmentAddBinding
import com.example.contact.fragment.FragmentViewModel
import com.example.contact.fragment.data.Contact


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: FragmentViewModel by activityViewModels()

    private lateinit var navigationView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView = view

        checkRadioBtn()

        binding.backButton.setOnClickListener {
            fragmentTransaction()
        }
        binding.saveButton.setOnClickListener {
            saveContact()
        }
    }

    private fun fragmentTransaction() {
        Navigation.findNavController(navigationView).navigate(R.id.action_addFragment_to_listFragment)
    }

    private fun saveContact() {
        val name = binding.editName.text.toString()
        val phoneEmail = binding.editPhoneEmail.text.toString()
        val rbId = binding.rgAddFragment.checkedRadioButtonId
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.enter_name), Toast.LENGTH_SHORT)
                .show()
        } else {

            if (phoneEmail.isEmpty() || (phoneEmail.isNotEmpty() && (checkEmailPhone(phoneEmail)))) {
                fragmentViewModel.addContact(
                    Contact(
                        contactName = name,
                        contactPhoneEmail = phoneEmail,
                        rbId
                    )
                )
                fragmentTransaction()
            }
        }
    }

    private fun checkEmailPhone(phoneEmail: String): Boolean =
        when (binding.rgAddFragment.checkedRadioButtonId) {
            R.id.rbEmail -> phoneEmail.checkEditEmail().apply {
                if (!this) Toast.makeText(requireContext(),
                    R.string.toast_inc_email,
                    Toast.LENGTH_SHORT).show()
            }
            else -> phoneEmail.checkEditNumber().apply {
                if (!this) Toast.makeText(requireContext(),
                    R.string.toast_inc_phone,
                    Toast.LENGTH_SHORT).show()
            }
        }

    //update title editText
    private fun checkRadioBtn() {
        binding.rgAddFragment.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                binding.rbEmail.id -> {
                    binding.textTitlePhoneEmail.text = getString(R.string.email_number)
                    binding.editPhoneEmail.setHint(getString(R.string.edit_mask_email))
                    binding.editPhoneEmail.inputType = InputType.TYPE_CLASS_TEXT
                }
                binding.rbPhone.id -> {
                    binding.textTitlePhoneEmail.text = getString(R.string.phone_number)
                    binding.editPhoneEmail.setHint(getString(R.string.edit_mask_phone))
                    binding.editPhoneEmail.inputType = InputType.TYPE_CLASS_PHONE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}