package com.sdk.mvvmroomdb103.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val noteTitle: String,
    val noteContent: String,
    val time: String
): Parcelable