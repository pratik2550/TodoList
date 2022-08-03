package com.example.todolistwithroomdatabase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistwithroomdatabase.database.TodoList
import com.example.todolistwithroomdatabase.database.TodoListDatabase
import com.example.todolistwithroomdatabase.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    val readAllDate: LiveData<List<TodoList>>
    private val repository: TodoRepository

    init {
        val todoDao = TodoListDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllDate = repository.readAllData
    }

    fun addTodoList(todoList: TodoList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todoList)
        }
    }

    fun updateTodoList(todoList: TodoList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todoList)
        }
    }

    fun deleteTodo(todoList: TodoList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todoList)
        }
    }

    fun deleteAllTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTodd()
        }
    }
}