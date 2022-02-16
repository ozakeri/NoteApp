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

    private var colorValueClicked = "#000000"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSingleNoteBinding = FragmentSingleNoteBinding.inflate(inflater, container, false)

        fragmentSingleNoteBinding.singleNoteFragment = this

        return fragmentSingleNoteBinding.root

    }

    fun onAddNoteClick(view: View) {

    }

    fun onColorViewClick(view: View) {
        uncheckAllImage()

        fragmentSingleNoteBinding.apply {
            when(view.id){
                this.check1.id -> colorValueClicked = "#64C8FD"
                this.check2.id -> colorValueClicked = "#8069FF"
                this.check3.id -> colorValueClicked = "#FFCC36"
                this.check4.id -> colorValueClicked = "#D77FFD"
                this.check5.id -> colorValueClicked = "#FF419A"
                this.check6.id -> colorValueClicked = "#7FFB76"
            }
        }

        view.visibility = View.VISIBLE
    }

    private fun uncheckAllImage() {
        fragmentSingleNoteBinding.apply {
            this.check1.visibility = View.GONE
            this.check2.visibility = View.GONE
            this.check3.visibility = View.GONE
            this.check4.visibility = View.GONE
            this.check5.visibility = View.GONE
            this.check6.visibility = View.GONE
        }
    }

}