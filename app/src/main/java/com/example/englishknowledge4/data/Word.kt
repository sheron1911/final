package com.example.englishknowledge4.data

data class Word(
    val name: String,
    val meaning: String,
    val partOfSpeech: String,
    val exampleSentence: String,
    val exampleTranslation: String, // 新增：例句的中文翻译
    val note: String // 新增：註解
)