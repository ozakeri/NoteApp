package com.example.mynotapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mynotapplication.databinding.FragmentSingleNoteBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SingleNotFragment : Fragment() {

    lateinit var fragmentSingleNoteBinding: FragmentSingleNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSingleNoteBinding = FragmentSingleNoteBinding.inflate(inflater, container, false)

        fragmentSingleNoteBinding.buttonSecond
        return fragmentSingleNoteBinding.root

    }

}