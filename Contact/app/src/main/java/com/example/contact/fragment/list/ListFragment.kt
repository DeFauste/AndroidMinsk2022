package com.example.contact.fragment.list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.Visibility
import com.example.contact.*
import com.example.contact.databinding.FragmentListBinding
import com.example.contact.fragment.add.AddFragment
import com.example.contact.fragment.edit.EditFragment

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentManager = parentFragmentManager
        binding.btnAddContact.setOnClickListener {
            fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right).replace(R.id.fragment_container_view, AddFragment())
                .addToBackStack(null).commit()
        }
        binding.oneContact.itemContact.setOnClickListener {
            fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right).replace(R.id.fragment_container_view, EditFragment())
                .addToBackStack(null).commit()
        }
        contactUpdate()
    }

    private fun contactUpdate() {
        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_CONTACT, Context.MODE_PRIVATE)
        val name = sharedPreferences.getString(CONTACT_NAME, null)
        if (name != null) {
            binding.oneContact.root.visibility = View.VISIBLE
            binding.textNotContact.visibility = View.GONE

            binding.oneContact.nameContact.text = name
            val phoneEmail = sharedPreferences.getString(CONTACT_PHONE_EMAIL, null)
            if (phoneEmail != null)
                binding.oneContact.numberPhoneContact.text = phoneEmail
        }else {
            binding.oneContact.root.visibility = View.GONE
            binding.textNotContact.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}