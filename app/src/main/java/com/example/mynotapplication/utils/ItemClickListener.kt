package com.example.mynotapplication.utils

import android.view.View
import com.example.mynotapplication.database.EntitiyNote

interface ItemClickListener {

    fun itemClick(entitiyNote: EntitiyNote)

    fun deleteItem(view : View, entitiyNote: EntitiyNote)
}