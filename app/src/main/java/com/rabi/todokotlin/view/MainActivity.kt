package com.rabi.todokotlin.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rabi.todokotlin.R
import com.rabi.todokotlin.view.adapter.RvAdapter
import com.rabi.todokotlin.model.data.Todo
import com.rabi.todokotlin.databinding.ActivityMainBinding
import com.rabi.todokotlin.db.MyDao
import com.rabi.todokotlin.db.MyDatabase
import com.rabi.todokotlin.viewmodel.MainViewModel
import com.rabi.todokotlin.viewmodel.MainViewModelFactory
import com.rabi.todokotlin.viewmodel.repo.MainRepository

class MainActivity : AppCompatActivity(), UpdateListner {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mDao : MyDao
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mDao = MyDatabase.getDB(applicationContext).myDao()
        val repo = MainRepository(mDao)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repo))[MainViewModel::class.java]


        mRv = binding.todoRv
        mRv.layoutManager = LinearLayoutManager(this)
        mainViewModel.getAll().observe(this) {
            mRv.adapter = RvAdapter(it, this)
        }

        binding.floatingActionButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.add_task)

        val todoEt = dialog.findViewById<EditText>(R.id.editTextTextPersonName)

        dialog.findViewById<Button>(R.id.button).setOnClickListener {
            if (todoEt.text.toString() != "") {
                val todo = todoEt.text.toString()
                val mt = Todo(0, todo, false)
                mainViewModel.insert(mt)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Empty Todo", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()


    }

    override fun updateTodo(todo: Todo) {
        mainViewModel.update(todo )
    }

}