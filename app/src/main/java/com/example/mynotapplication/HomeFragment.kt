package com.example.mynotapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.mynotapplication.adapter.PinnedRvAdapter
import com.example.mynotapplication.adapter.UnPinnedRvAdapter
import com.example.mynotapplication.database.EntitiyNote
import com.example.mynotapplication.databinding.FragmentHomeBinding
import com.example.mynotapplication.utils.ItemClickListener
import com.example.mynotapplication.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {

    lateinit var fragmentHomeBinding: FragmentHomeBinding
    val viewmodel: NoteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        fragmentHomeBinding.homeFragment = this

        setupPinnedRv()
        setupUnPinnedRv()


        return fragmentHomeBinding.root
    }

    fun setupUnPinnedRv() {

        viewmodel.liveData.observe(viewLifecycleOwner) { lisatData ->
            val unPinnedRvList: ArrayList<EntitiyNote> = ArrayList()
            lisatData.forEach {
                if (!it.notes.isPine) {
                    unPinnedRvList.add(it)
                }
            }

            if (unPinnedRvList.isEmpty())
                fragmentHomeBinding.textView3.visibility = View.VISIBLE
            else
                fragmentHomeBinding.textView3.visibility = View.GONE


            fragmentHomeBinding.upcomingRv.adapter = UnPinnedRvAdapter(unPinnedRvList, this)
        }

    }

    fun setupPinnedRv() {

        viewmodel.liveData.observe(viewLifecycleOwner) { lisatData ->
            val pinnedRvList: ArrayList<EntitiyNote> = ArrayList()
            lisatData.forEach {
                if (it.notes.isPine) {
                    pinnedRvList.add(it)
                }
            }

            if (pinnedRvList.isEmpty())
                fragmentHomeBinding.pinnedCon.visibility = View.GONE
            else
                fragmentHomeBinding.pinnedCon.visibility = View.VISIBLE

            fragmentHomeBinding.pinnedRv.adapter = PinnedRvAdapter(pinnedRvList, this)
        }

    }

    fun fabOnClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_singleNotFragment)
    }

    override fun itemClick(entitiyNote: EntitiyNote) {
        val bundle = bundleOf("entityNote" to entitiyNote)
        Navigation.findNavController(fragmentHomeBinding.root).navigate(R.id.action_homeFragment_to_singleNotFragment,bundle)
    }

    override fun deleteItem(view: View, entitiyNote: EntitiyNote) {
        val popupMenu = PopupMenu(requireActivity(),view)
        popupMenu.inflate(R.menu.action_delete)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
            when(it.itemId){
                R.id.delete ->{
                    deleteFromDb(entitiyNote)
                    true
                }
                else -> return@OnMenuItemClickListener false
            }
        })
    }

    private fun deleteFromDb(entitiyNote: EntitiyNote) {
        viewmodel.deleteItems(entitiyNote)
    }

}