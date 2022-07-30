package com.example.todolistwithroomdatabase

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistwithroomdatabase.databinding.FragmentListBinding

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var mTodoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container,false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        val adapter = TodoListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        mTodoViewModel.readAllDate.observe(viewLifecycleOwner, Observer { todo->
            adapter.setData(todo)
        })

        return binding.root
    }

}