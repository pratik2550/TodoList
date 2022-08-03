package com.example.todolistwithroomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.todolistwithroomdatabase.database.TodoDAO
import com.example.todolistwithroomdatabase.database.TodoList

class TodoRepository(private val todoDao: TodoDAO) {

    val readAllData: LiveData<List<TodoList>> = todoDao.readAllTodo()

    suspend fun addTodo(todoList: TodoList) {
        todoDao.addTodo(todoList)
    }

    suspend fun updateTodo(todoList: TodoList) {
        todoDao.updateTodo(todoList)
    }

    suspend fun deleteTodo(todoList: TodoList) {
        todoDao.deleteTodo(todoList)
    }

    suspend fun deleteAllTodd() {
        todoDao.deleteAllTodo()
    }
}