package com.example.todolistwithroomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDAO {

    @Insert
    suspend fun addTodo(todoList: TodoList)

    @Update
    suspend fun updateTodo(todoList: TodoList)

    @Query("SELECT * FROM todoList ORDER BY id ASC")
    fun readAllTodo(): LiveData<List<TodoList>>
}