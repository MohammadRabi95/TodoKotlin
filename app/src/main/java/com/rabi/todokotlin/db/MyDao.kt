package com.rabi.todokotlin.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rabi.todokotlin.model.data.Todo

@Dao
interface MyDao {

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("Select * from Todo")
    fun getAll(): LiveData<List<Todo>>
}