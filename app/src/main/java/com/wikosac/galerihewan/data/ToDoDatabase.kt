package com.wikosac.galerihewan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wikosac.galerihewan.utils.Constants.DATABASE_NAME

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract val toDoDao: ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null
        fun getInstance(context: Context): ToDoDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}