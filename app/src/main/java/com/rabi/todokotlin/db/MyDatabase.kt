package com.rabi.todokotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rabi.todokotlin.model.data.Todo

@Database(entities = [Todo::class], version = 2, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao() : MyDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getDB(context: Context) : MyDatabase {
            if(INSTANCE == null)
                INSTANCE = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java,
                    "todo_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

            return INSTANCE!!
            }
        }
    }
