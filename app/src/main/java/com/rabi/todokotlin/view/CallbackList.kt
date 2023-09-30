package com.rabi.todokotlin.view

import com.rabi.todokotlin.model.data.Todo

interface CallbackList {

    fun newList(list: List<Todo>)
}