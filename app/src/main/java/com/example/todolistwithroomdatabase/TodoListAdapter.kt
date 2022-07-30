package com.example.todolistwithroomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistwithroomdatabase.databinding.TodoListRecyclerViewBinding

class TodoListAdapter(): RecyclerView.Adapter<TodoListAdapter.MyViewHolder>() {

    private var todoList = emptyList<TodoList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TodoListRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = todoList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(todo: List<TodoList>) {
        this.todoList = todo
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: TodoListRecyclerViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoList) {
            binding.listItem = item
        }
    }
}