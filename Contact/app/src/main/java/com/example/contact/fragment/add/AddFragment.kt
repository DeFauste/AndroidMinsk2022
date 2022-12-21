package com.example.contact.fragment.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.contact.R


class AddFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }
}