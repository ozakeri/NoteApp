package com.example.mynotapplication

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.mynotapplication.database.EntitiyNote
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
    private var colorValueClicked = "#64C8FD"
    private val noteViewModel: NoteViewModel by viewModels()
    lateinit var mainActivity: MainActivity
    lateinit var navController: NavController
    private var isPinned = false
    lateinit var entitiyNote: EntitiyNote
    private var isUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSingleNoteBinding = FragmentSingleNoteBinding.inflate(inflater, container, false)
        fragmentSingleNoteBinding.singleNoteFragment = this
        return fragmentSingleNoteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(view)
        setHasOptionsMenu(true)
        getData()
    }

    private fun getData() {
        if (arguments != null) {
            entitiyNote = arguments!!.getParcelable("entityNote")!!
            fragmentSingleNoteBinding.titleEdtx.setText(entitiyNote.notes.title)
            fragmentSingleNoteBinding.noteEdtx.setText(entitiyNote.notes.description)
            isPinned = entitiyNote.notes.isPine
            setColorForUpdate(entitiyNote)
            isUpdate = true
        }
    }


    fun onAddNoteClick(view: View) {

        if (isUpdate){
            fragmentSingleNoteBinding.apply {
                if (this.titleEdtx.text.isNullOrEmpty()) {
                    Snackbar.make(this.mainCoord, "Pls Enter Your Title...", Snackbar.LENGTH_SHORT)
                        .show()
                } else if (this.noteEdtx.text.isNullOrEmpty()) {
                    Snackbar.make(this.mainCoord, "Pls Enter Your Note...", Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    entitiyNote.notes.title = this.titleEdtx.text.toString()
                    entitiyNote.notes.description = this.noteEdtx.text.toString()
                    entitiyNote.notes.color = colorValueClicked;
                    entitiyNote.notes.isPine = isPinned

                    noteViewModel.updateNote(entitiyNote)

                    view.findNavController().navigate(R.id.action_singleNotFragment_to_homeFragment)
                }
            }
        }else{
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

                    val notes = Notes(title, note, color, isPinned)

                    noteViewModel.insertNote(notes)

                    view.findNavController().navigate(R.id.action_singleNotFragment_to_homeFragment)
                }
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

    fun setColorForUpdate(entitiyNote: EntitiyNote) {
        uncheckAllImage()
        fragmentSingleNoteBinding.apply {
            when (entitiyNote.notes.color) {
                "#64C8FD" -> this.check1.visibility = View.VISIBLE
                "#8069FF" -> this.check2.visibility = View.VISIBLE
                "#FFCC36" -> this.check3.visibility = View.VISIBLE
                "#D77FFD" -> this.check4.visibility = View.VISIBLE
                "#FF419A" -> this.check5.visibility = View.VISIBLE
                "#7FFB76" -> this.check6.visibility = View.VISIBLE
            }
        }
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

    fun setupToolbar(view: View) {
        navController = Navigation.findNavController(view)
        val appBarConfiguration = AppBarConfiguration.Builder(R.id.singleNotFragment).build()
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // for change left icon of toolbar (back Icon)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.singleNotFragment) {
                toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.singlenote_menu, menu)

        val item = menu.findItem(R.id.pinitem)
        if (isPinned){
            item.icon = ContextCompat.getDrawable(requireActivity(),R.drawable.baseline_push_pin_black_24dp)
        }else{
            item.icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_outline_push_pin_24)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.pinitem ->
                if (!isPinned) {
                    item.icon = ContextCompat.getDrawable(
                        requireActivity(), R.drawable.baseline_push_pin_black_24dp
                    )
                    isPinned = true
                    true
                } else {
                    item.icon = ContextCompat.getDrawable(
                        requireActivity(), R.drawable.ic_outline_push_pin_24
                    )
                    isPinned = false
                    true
                }
            android.R.id.home -> {
                mainActivity.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
    }

}