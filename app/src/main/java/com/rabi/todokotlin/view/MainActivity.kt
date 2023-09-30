package com.rabi.todokotlin.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabi.todokotlin.R
import com.rabi.todokotlin.view.adapter.RvAdapter
import com.rabi.todokotlin.model.data.Todo
import com.rabi.todokotlin.databinding.ActivityMainBinding
import com.rabi.todokotlin.db.MyDatabase

class MainActivity : AppCompatActivity(), UpdateListner {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list : List<Todo>
    private lateinit var mDB : MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mDB = MyDatabase.getDB(applicationContext)

        val mRv = binding.todoRv
        mRv.layoutManager = LinearLayoutManager(this)
        list = mDB.myDao().getAll()
        mRv.adapter = RvAdapter(list, this)
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
                mDB.myDao().insertTodo(mt)
                list = emptyList()
                list = mDB.myDao().getAll()
                binding.todoRv.adapter = RvAdapter(list, this)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Empty Todo", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()


    }

    override fun updateTodo(todo: Todo) {
        mDB.myDao().update(todo)
    }
}