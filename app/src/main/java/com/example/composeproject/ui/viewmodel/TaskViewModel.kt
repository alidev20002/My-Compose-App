package com.example.composeproject.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {

    var tasks = mutableStateListOf<String>()

    fun addTask(task: String) {
        tasks.add(task)
    }

    fun removeTask(index: Int) {
        tasks.removeAt(index)
    }
}