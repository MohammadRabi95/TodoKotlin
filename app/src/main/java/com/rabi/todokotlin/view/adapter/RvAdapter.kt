package com.rabi.todokotlin.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rabi.todokotlin.R
import com.rabi.todokotlin.db.MyDatabase
import com.rabi.todokotlin.model.data.Todo
import com.rabi.todokotlin.view.UpdateListner

class RvAdapter(private val list: List<Todo>,private val listner: UpdateListner) : RecyclerView.Adapter<RvAdapter.Holder>() {


    class Holder(itemView: View) : ViewHolder(itemView) {
        val chkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.chkBox.text = list[position].todo
        holder.chkBox.isChecked = list[position].isCompleted

        holder.chkBox.setOnCheckedChangeListener{ _, isChecked ->
            run {
                list[position].isCompleted = isChecked
                listner.updateTodo(list[position])

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}