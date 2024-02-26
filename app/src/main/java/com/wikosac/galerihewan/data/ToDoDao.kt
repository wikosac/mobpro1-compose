package com.wikosac.galerihewan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTask: ToDoTask)

    @Update
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)

}