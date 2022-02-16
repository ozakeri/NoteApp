package com.example.mynotapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mynotapplication.databinding.FragmentSingleNoteBinding
import com.example.mynotapplication.model.Notes
import com.example.mynotapplication.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class SingleNotFragment : Fragment() {

    lateinit var fragmentSingleNoteBinding: FragmentSingleNoteBinding
    private var colorValueClicked = "#000000"
    private val noteViewModel: NoteViewModel by viewModels()

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

        fragmentSingleNoteBinding.apply {
            if (this.titleEdtx.text.isNullOrEmpty()) {
                Snackbar.make(this.mainCoord, "Pls Enter Your Title...", Snackbar.LENGTH_SHORT)
                    .show()
            } else if (this.noteEdtx.text.isNullOrEmpty()) {
                Snackbar.make(this.mainCoord, "Pls Enter Your Note...", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                val title = this.titleEdtx.text.toString()
                val note = this.noteEdtx.text.toString()
                val color = colorValueClicked;

                val notes = Notes(title, note, color, false)

                noteViewModel.insertNote(notes)

                view.findNavController().navigate(R.id.action_singleNotFragment_to_homeFragment)
            }
        }

    }

    fun onColorViewClick(view: View) {
        uncheckAllImage()

        fragmentSingleNoteBinding.apply {
            when (view.id) {
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