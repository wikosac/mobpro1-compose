package com.wikosac.galerihewan.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.wikosac.galerihewan.data.ToDoTask
import com.wikosac.galerihewan.ui.SharedViewModel
import com.wikosac.galerihewan.utils.Action
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel,
    selectedTask: ToDoTask?,
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val context = LocalContext.current

    var currentTime by remember { mutableStateOf(System.currentTimeMillis()) }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            currentTime = System.currentTimeMillis()
            delay(1000)
        }
    }

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (title.isNotEmpty() && description.isNotEmpty()) {
                            navigateToListScreen(action)
                            sharedViewModel.time.value = currentTime
                        } else {
                            if (title.isEmpty()) showToast(context, "Judul tidak boleh kosong")
                            if (description.isEmpty()) showToast(
                                context = context,
                                message = "Deskripsi tidak boleh kosong"
                            )
                        }
                    }
                },
                selectedTask = selectedTask
            )
        },
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {
                TaskContent(
                    title = title,
                    onTitleChange = { newTitle ->
                        sharedViewModel.title.value = newTitle
                    },
                    description = description,
                    onDescriptionChange = { newDesc ->
                        sharedViewModel.description.value = newDesc
                    }
                )
            }
        }
    )
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}