package com.example.todolistwithroomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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
}