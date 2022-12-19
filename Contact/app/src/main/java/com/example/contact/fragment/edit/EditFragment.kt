package com.example.contact.fragment.edit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.contact.databinding.FragmentEditBinding



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
}