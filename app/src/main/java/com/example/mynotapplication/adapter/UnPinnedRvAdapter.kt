package com.example.mynotapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotapplication.database.EntitiyNote
import com.example.mynotapplication.databinding.UpcomingRvItemsBinding
import com.example.mynotapplication.utils.ItemClickListener

class UnPinnedRvAdapter(
    private var unPinnedNoteList: ArrayList<EntitiyNote>,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<UnPinnedRvAdapter.CustomViewHolder>() {

    class CustomViewHolder(val binding: UpcomingRvItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entitiyNote: EntitiyNote, listener: ItemClickListener) {
            binding.pinnedtitle
            binding.pinnedtitle.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    entitiyNote.notes.title, binding.pinnedtitle.textMetricsParamsCompat, null
                )
            )

            binding.pinneddescription.text = entitiyNote.notes.description
            binding.upcomingCard.setCardBackgroundColor(Color.parseColor(entitiyNote.notes.color))

            binding.upcomingCard.setOnClickListener {
                listener.itemClick(entitiyNote)
            }

            binding.imageFilterButton2.setOnClickListener {
                listener.deleteItem(binding.imageFilterButton2,entitiyNote)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding: UpcomingRvItemsBinding = UpcomingRvItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(unPinnedNoteList[position], listener)
    }

    override fun getItemCount(): Int {
        return unPinnedNoteList.size
    }
}