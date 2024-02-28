package com.wikosac.galerihewan.ui.screens.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.wikosac.galerihewan.data.ToDoTask
import com.wikosac.galerihewan.ui.SharedViewModel
import com.wikosac.galerihewan.utils.Action

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel,
    selectedTask: ToDoTask?,
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = navigateToListScreen,
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