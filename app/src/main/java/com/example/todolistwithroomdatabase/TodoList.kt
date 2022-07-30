package com.example.todolistwithroomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoList")
data class TodoList(
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val title: String,
    val description: String,
    val status: Boolean = false
)
