package com.wikosac.galerihewan.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wikosac.galerihewan.data.ToDoDao
import com.wikosac.galerihewan.data.ToDoTask
import com.wikosac.galerihewan.utils.Action
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(
    private val toDoDao: ToDoDao
) : ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val time: MutableState<Long> = mutableStateOf(0L)

    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> = _allTasks

    fun getAllTasks() {
        viewModelScope.launch {
            toDoDao.getAllTasks().collect {
                _allTasks.value = it
            }
        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            toDoDao.getSelectedTask(taskId).collect {
                _selectedTask.value = it
            }
        }
    }

    fun updateTaskFields(selectedTask: ToDoTask?) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            time.value = selectedTask.time
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            time.value = 0L
        }
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                title = title.value,
                description = description.value,
                time = time.value
            )
            toDoDao.addTask(toDoTask)
        }
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                time = time.value
            )
            toDoDao.updateTask(toDoTask)
        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                time = time.value
            )
            toDoDao.deleteTask(toDoTask)
        }
    }

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> addTask()
            Action.UPDATE -> updateTask()
            Action.DELETE -> deleteTask()
            else -> {}
        }
        this.action.value = Action.NO_ACTION
    }

}
