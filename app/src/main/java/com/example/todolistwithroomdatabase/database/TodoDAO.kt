package com.example.todolistwithroomdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistwithroomdatabase.database.TodoList

@Dao
interface TodoDAO {

    @Insert
    suspend fun addTodo(todoList: TodoList)

    @Update
    suspend fun updateTodo(todoList: TodoList)

    @Delete
    suspend fun deleteTodo(todoList: TodoList)

    @Query("DELETE FROM todoList")
    suspend fun deleteAllTodo()

    @Query("SELECT * FROM todoList ORDER BY id ASC")
    fun readAllTodo(): LiveData<List<TodoList>>
}