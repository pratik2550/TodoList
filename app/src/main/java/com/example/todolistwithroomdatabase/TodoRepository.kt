package com.example.todolistwithroomdatabase

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao:TodoDAO) {

    val readAllData: LiveData<List<TodoList>> = todoDao.readAllTodo()

    suspend fun addTodo(todoList: TodoList) {
        todoDao.addTodo(todoList)
    }

    suspend fun updateTodo(todoList: TodoList) {
        todoDao.updateTodo(todoList)
    }
}