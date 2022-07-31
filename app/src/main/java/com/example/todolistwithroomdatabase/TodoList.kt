package com.example.todolistwithroomdatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "todoList")
data class TodoList(
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val title: String,
    val description: String,
    val status: Boolean = false
): Parcelable
