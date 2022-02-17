package com.example.mynotapplication.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynotapplication.model.Notes

@Entity(tableName = "noteTable")
data class EntitiyNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val notes: Notes
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("notes")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntitiyNote> {
        override fun createFromParcel(parcel: Parcel): EntitiyNote {
            return EntitiyNote(parcel)
        }

        override fun newArray(size: Int): Array<EntitiyNote?> {
            return arrayOfNulls(size)
        }
    }
}
