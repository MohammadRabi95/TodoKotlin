package com.rabi.todokotlin.view

import com.rabi.todokotlin.model.data.Todo

interface UpdateListner {

    fun updateTodo(todo: Todo)
}