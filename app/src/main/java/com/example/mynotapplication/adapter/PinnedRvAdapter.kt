package com.example.mynotapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotapplication.database.EntitiyNote
import com.example.mynotapplication.databinding.PinnedRvItemsBinding
import com.example.mynotapplication.model.Notes

class PinnedRvAdapter(private var pinnedNoteList: ArrayList<EntitiyNote>) :
    RecyclerView.Adapter<PinnedRvAdapter.CustomViewHolder>() {

    class CustomViewHolder(val binding: PinnedRvItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entitiyNote: EntitiyNote) {
            binding.pinnedtitle.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    entitiyNote.notes.title, binding.pinnedtitle.textMetricsParamsCompat, null
                )
            )

            binding.pinneddescription.text = entitiyNote.notes.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding: PinnedRvItemsBinding = PinnedRvItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(pinnedNoteList[position])
    }

    override fun getItemCount(): Int {
        return pinnedNoteList.size
    }
}