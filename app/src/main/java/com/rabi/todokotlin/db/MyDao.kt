package com.rabi.todokotlin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rabi.todokotlin.model.data.Todo
import java.util.*

@Dao
interface MyDao {

    @Insert
    fun insertTodo(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Query("Select * from Todo")
    fun getAll(): List<Todo>
}