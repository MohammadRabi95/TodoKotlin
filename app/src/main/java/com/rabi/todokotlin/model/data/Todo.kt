package com.rabi.todokotlin.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class Todo(
                @PrimaryKey(autoGenerate = true)
                val id: Int,
                var todo: String,
                var isCompleted: Boolean)
