package com.example.todolistwithroomdatabase.fragement

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolistwithroomdatabase.R
import com.example.todolistwithroomdatabase.database.TodoList
import com.example.todolistwithroomdatabase.viewModel.TodoViewModel
import com.example.todolistwithroomdatabase.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var binding: FragmentUpdateBinding
    lateinit var mTodoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.editTextTitleUD.setText(args.currentTodo.title)
        binding.editTextDescriptionUD.setText(args.currentTodo.description)

        binding.updateButton.setOnClickListener {
            updateItem()
        }

//        setHasOptionsMenu(true)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
                inflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.menu_delete) {
                    deleteUser()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        return binding.root
    }

    private fun updateItem() {
        val title = binding.editTextTitleUD.text.toString()
        val desc = binding.editTextDescriptionUD.text.toString()

        if (inputCheck(title, desc)) {
            val updatedTodo = TodoList(args.currentTodo.ID, title, desc, true)
            mTodoViewModel.updateTodoList(updatedTodo)
            Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(title: String, desc: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(desc))
    }


    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTodoViewModel.deleteTodo(args.currentTodo)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentTodo.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentTodo.title}?")
        builder.setMessage("Are you want to delete ${args.currentTodo.title}?")
        builder.create().show()
    }

}