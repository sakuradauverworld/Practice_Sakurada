package com.example.tangoapp

import kotlinx.serialization.Serializable

@Serializable
data class WordList(val word: Word) {
     var wordList = mutableListOf(word)
}
