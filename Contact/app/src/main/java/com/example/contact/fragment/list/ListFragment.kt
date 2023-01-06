package com.example.contact.fragment.list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.transition.Visibility
import com.example.contact.*
import com.example.contact.databinding.FragmentListBinding
import com.example.contact.fragment.FragmentViewModel
import com.example.contact.fragment.add.AddFragment
import com.example.contact.fragment.data.Contact
import com.example.contact.fragment.data.RecyclerViewAdapter
import com.example.contact.fragment.edit.EditFragment

class ListFragment : Fragment(), RecyclerViewAdapter.Listener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: FragmentViewModel by activityViewModels()

    private val adapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
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
        if(fragmentViewModel.getValueContacts() == 0){
            binding.rcViewContacts.visibility = View.GONE
            binding.textNotContact.visibility = View.VISIBLE
        }else {
            binding.rcViewContacts.visibility = View.VISIBLE
            binding.textNotContact.visibility = View.GONE
            initRecycler()
        }

        binding.btnAddContact.setOnClickListener {
            fragmentTransaction(AddFragment(), R.id.fragment_container_view)
        }
    }

    private fun fragmentTransaction(f: Fragment, idLayout: Int) {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
            .replace(idLayout, f)
            .addToBackStack(null).commit()
    }

    private fun initRecycler() {
        binding.apply {
            rcViewContacts.layoutManager = LinearLayoutManager(requireContext())
            rcViewContacts.adapter = adapter

            fragmentViewModel.getContacts().observe(activity as LifecycleOwner) {
                adapter.addContact(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(contact: Contact) {
        fragmentViewModel.clickItem = contact
        fragmentTransaction(EditFragment(), R.id.fragment_container_view)
    }
}