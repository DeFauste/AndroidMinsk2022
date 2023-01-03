package com.example.contact.fragment.add

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.contact.*
import com.example.contact.databinding.FragmentAddBinding
import com.example.contact.fragment.list.ListFragment
import java.util.regex.Matcher
import java.util.regex.Pattern


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkRadioBtn()

        saveContact()

        val fragmentManager = parentFragmentManager

        binding.backButton.setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view,
                    ListFragment())
                .addToBackStack(null).commit()
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

                if (phoneEmail.isEmpty() || (phoneEmail.isNotEmpty() && (checkEmailPhone(phoneEmail)))) {
                    sharedPreferences.edit {
                        putString(CONTACT_NAME, name)
                        putString(CONTACT_PHONE_EMAIL, phoneEmail)
                        putInt(CONTACT_CHECKED_RB, binding.rgAddFragment.checkedRadioButtonId)
                        apply()
                    }
                    val fragmentManager = parentFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, ListFragment())
                        .addToBackStack(null).commit()
                }
            }
        }
    }

    private fun checkEmailPhone(phoneEmail: String): Boolean =
        when (binding.rgAddFragment.checkedRadioButtonId) {
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