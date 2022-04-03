package com.example.todoapp

import kotlinx.serialization.Serializable

@Serializable
data class TaskList(val task: Task) {
    var taskList = mutableListOf(task)
}
