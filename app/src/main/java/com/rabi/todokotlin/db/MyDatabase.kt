package com.rabi.todokotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rabi.todokotlin.model.data.Todo

@Database(entities = [Todo::class], version = 4, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getDB(context: Context): MyDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, MyDatabase::class.java, "todo_db"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }
}
