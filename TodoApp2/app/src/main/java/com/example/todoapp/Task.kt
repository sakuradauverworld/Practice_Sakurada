package com.example.todoapp

import kotlinx.serialization.Serializable


@Serializable
data class Task(val title: String,val fixedDate: String)
