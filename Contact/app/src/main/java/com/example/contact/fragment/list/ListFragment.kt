package com.example.contact.fragment.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact.*
import com.example.contact.databinding.FragmentListBinding
import com.example.contact.fragment.FragmentViewModel
import com.example.contact.fragment.data.Contact
import com.example.contact.fragment.data.RecyclerViewAdapter

class ListFragment : Fragment(), RecyclerViewAdapter.Listener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: FragmentViewModel by activityViewModels()

    private lateinit var navigationView: View

    private val adapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView = view
        if(fragmentViewModel.getValueContacts() == 0){
            binding.rcViewContacts.visibility = View.GONE
            binding.textNotContact.visibility = View.VISIBLE
        }else {
            binding.rcViewContacts.visibility = View.VISIBLE
            binding.textNotContact.visibility = View.GONE
            initRecycler()
        }

        binding.btnAddContact.setOnClickListener {
            fragmentTransaction(R.id.action_listFragment_to_addFragment)
        }
    }
    private fun fragmentTransaction(idNavigation: Int) {
        Navigation.findNavController(navigationView).navigate(idNavigation)
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
        fragmentTransaction(R.id.action_listFragment_to_editFragment)
    }
}