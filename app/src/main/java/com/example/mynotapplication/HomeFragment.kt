package com.example.mynotapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.mynotapplication.adapter.PinnedRvAdapter
import com.example.mynotapplication.databinding.FragmentHomeBinding
import com.example.mynotapplication.model.Notes
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Named

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        fragmentHomeBinding.homeFragment = this

        setupPinnedRv()

        return fragmentHomeBinding.root
    }


    fun setupPinnedRv(){
        val pinnedRvList : ArrayList<Notes> = ArrayList()
   /*     pinnedRvList.add(Notes("title 1","description 1"))
        pinnedRvList.add(Notes("title 2","description 2"))
        pinnedRvList.add(Notes("title 3","description 3"))*/

        fragmentHomeBinding.pinnedRv.adapter = PinnedRvAdapter(pinnedRvList)
    }

    fun fabOnClick(view : View){
        view.findNavController().navigate(R.id.action_homeFragment_to_singleNotFragment)
    }
}