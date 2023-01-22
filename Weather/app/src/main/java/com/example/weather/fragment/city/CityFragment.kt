package com.example.weather.fragment.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.weather.databinding.FragmentCityBinding
import com.example.weather.fragment.FragmentViewModel
import kotlinx.coroutines.flow.first


class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: FragmentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddCity.setOnClickListener {
            CustomInputDialogFragment().show(
                childFragmentManager, CustomInputDialogFragment.TAG)
        }
        initRecyclerView()

    }

    private fun initRecyclerView() {
        lifecycleScope.launchWhenCreated {
            fragmentViewModel.readAllData().first()
        }
    }

}