package com.example.mynotapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mynotapplication.adapter.PinnedRvAdapter
import com.example.mynotapplication.database.EntitiyNote
import com.example.mynotapplication.databinding.FragmentHomeBinding
import com.example.mynotapplication.model.Notes
import com.example.mynotapplication.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding
    val viewmodel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        fragmentHomeBinding.homeFragment = this

        setupUnPinnedRv()

        return fragmentHomeBinding.root
    }


    fun setupUnPinnedRv() {

       viewmodel.liveData.observe(viewLifecycleOwner){ lisatData ->
           val pinnedRvList: ArrayList<EntitiyNote> = ArrayList()
           lisatData.forEach {
               if (!it.notes.isPine){
                   pinnedRvList.add(it)
               }
           }
           fragmentHomeBinding.upcomingRv.adapter = PinnedRvAdapter(pinnedRvList)
       }

    }

    fun fabOnClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_singleNotFragment)
    }
}