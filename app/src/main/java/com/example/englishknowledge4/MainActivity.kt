package com.example.englishknowledge4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishknowledge4.screens.VocabularyScreen
import com.example.englishknowledge4.screens.WordDetailScreen
import com.example.englishknowledge4.screens.AddWordScreen
import com.example.englishknowledge4.data.Word

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MaterialTheme(
        typography = Typography(
            displayLarge = TextStyle(fontSize = 24.sp),
            displayMedium = TextStyle(fontSize = 22.sp),
            displaySmall = TextStyle(fontSize = 20.sp),
            headlineLarge = TextStyle(fontSize = 22.sp),
            headlineMedium = TextStyle(fontSize = 20.sp),
            headlineSmall = TextStyle(fontSize = 18.sp),
            bodyLarge = TextStyle(fontSize = 18.sp),
            bodyMedium = TextStyle(fontSize = 16.sp),
            bodySmall = TextStyle(fontSize = 14.sp),
            labelLarge = TextStyle(fontSize = 16.sp),
            labelMedium = TextStyle(fontSize = 14.sp),
            labelSmall = TextStyle(fontSize = 12.sp)
        )
    ) {
        val selectedWord = remember { mutableStateOf<Word?>(null) }
        val wordList = remember {
            mutableStateListOf(
                Word(
                    name = "run",
                    meaning = "跑步",
                    partOfSpeech = "动词",
                    exampleSentence = "She likes to run every morning.",
                    exampleTranslation = "她喜欢每天早上跑步。",
                    note = "常用于表达运动或快速移动的动作。"
                ),
                Word(
                    name = "book",
                    meaning = "书籍",
                    partOfSpeech = "名词",
                    exampleSentence = "He is reading a book.",
                    exampleTranslation = "他正在看一本书。",
                    note = "书籍可以是任何形式的文字或图像的载体。"
                ),
                Word(
                    name = "beautiful",
                    meaning = "美丽的",
                    partOfSpeech = "形容词",
                    exampleSentence = "The view is beautiful.",
                    exampleTranslation = "风景非常美丽。",
                    note = "通常用于描述外观、声音或情感的吸引力。"
                ),
                Word(
                    name = "quickly",
                    meaning = "快速地",
                    partOfSpeech = "副词",
                    exampleSentence = "She runs quickly.",
                    exampleTranslation = "她跑得很快。",
                    note = "强调动作的速度，可以搭配动词使用。"
                )
            )
        }
        val isAddWordScreenVisible = remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                isAddWordScreenVisible.value -> {
                    AddWordScreen(
                        onBackClick = { isAddWordScreenVisible.value = false },
                        onAddWord = { newWord ->
                            wordList.add(newWord)
                            isAddWordScreenVisible.value = false
                        }
                    )
                }
                selectedWord.value != null -> {
                    WordDetailScreen(
                        word = selectedWord.value!!,
                        onBackClick = { selectedWord.value = null },
                        onUpdateWord = { updatedWord ->
                            val index = wordList.indexOf(selectedWord.value)
                            if (index >= 0) wordList[index] = updatedWord
                            selectedWord.value = null
                        },
                        onDeleteWord = {
                            wordList.remove(selectedWord.value)
                            selectedWord.value = null
                        }
                    )
                }
                else -> {
                    VocabularyScreen(
                        wordList = wordList,
                        onWordClick = { selectedWord.value = it },
                        onAddWordClick = { isAddWordScreenVisible.value = true }
                    )
                }
            }
        }
    }
}
