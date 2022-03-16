package com.example.tangoapp

import org.json.JSONObject

data class Word(val englishWord: String,val japaneseWord: String) {
    companion object {
        fun makeWordFromJson(json: JSONObject): Word {
            return Word(json.getString("englishWord"),json.getString("japaneseWord"))
        }
    }
}
