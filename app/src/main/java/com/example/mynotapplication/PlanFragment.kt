package com.example.mynotapplication

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynotapplication.databinding.FragmentHomeBinding
import com.example.mynotapplication.databinding.FragmentPlanBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PlanFragment : Fragment() {


    lateinit var fragmentPlanBinding: FragmentPlanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPlanBinding = FragmentPlanBinding.inflate(inflater, container, false)

        return fragmentPlanBinding.root
    }
}