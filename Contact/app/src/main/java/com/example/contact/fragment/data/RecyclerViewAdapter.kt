package com.example.contact.fragment.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.R
import com.example.contact.databinding.ItemContactBinding

class RecyclerViewAdapter(private val listener: Listener) : RecyclerView.Adapter<RecyclerViewAdapter.ContactHolder>() {

    private var contactList = ArrayList<Contact>()

    class ContactHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemContactBinding.bind(item)
        fun bind(contact: Contact, listener: Listener) = with(binding) {
            nameContact.text = contact.contactName
            numberPhoneContact.text = contact.contactPhoneEmail
            itemContact.setOnClickListener{
                listener.onClick(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(contactList[position], listener)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun addContact(contacts: ArrayList<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }
    interface Listener {
        fun onClick(contact: Contact)
    }
}