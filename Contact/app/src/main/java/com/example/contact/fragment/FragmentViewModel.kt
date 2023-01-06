package com.example.contact.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contact.fragment.data.Contact

class FragmentViewModel : ViewModel() {
    private val contactList = MutableLiveData<ArrayList<Contact>>()

    init {
        contactList.value = arrayListOf()
    }

    var clickItem: Contact = Contact("", "", 0)

    fun getContacts(): MutableLiveData<ArrayList<Contact>> {
        return contactList
    }

    fun getValueContacts(): Int {
        return contactList.value?.size ?: 0
    }

    fun deleteContact(contact: Contact) {
        contactList.value?.remove(contact)
    }
    fun addContact(contact: Contact) {
        contactList.value?.add(contact)
        contactList.value = contactList.value
    }
}