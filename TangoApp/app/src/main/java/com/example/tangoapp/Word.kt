package com.example.tangoapp

import kotlinx.serialization.Serializable

@Serializable
data class Word(val englishWord: String,val japaneseWord: String)