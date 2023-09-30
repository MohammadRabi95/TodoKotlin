package com.rabi.todokotlin.viewmodel.repo

import androidx.lifecycle.LiveData
import com.rabi.todokotlin.db.MyDao
import com.rabi.todokotlin.model.data.Todo

class MainRepository(private val myDao: MyDao) {

    fun getAll(): LiveData<List<Todo>> {
        return myDao.getAll()
    }

    suspend fun insert(todo: Todo) {
        myDao.insertTodo(todo)
    }

    suspend fun update(todo: Todo){
        myDao.update(todo)
    }
}