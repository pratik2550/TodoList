package com.example.todolistwithroomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistwithroomdatabase.database.TodoList
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
        holder.bind(currentItem)
        holder.binding.root.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.binding.root.findNavController().navigate(action)
        }
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