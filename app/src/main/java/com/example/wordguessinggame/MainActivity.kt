package com.example.wordguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.wordguessinggame.ui.theme.WordGuessingGameTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordGuessingGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class WordGameState(
    val numOfGuesses: Int = 4,
    val word: String, // can index string, no need for list
    val userGuess: String = "",
    val userGuessNum: Int = 0,
    val guessedCorrectWord: Boolean = false,
    val wonGame: Boolean = false,
    val lostGame: Boolean = false
)

class WordGameViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(WordGameState(
        word = "farm"
    ))
    val uiState: StateFlow<WordGameState> = _uiState
    fun onGuess() {
        _uiState.value = _uiState.value.copy(
            userGuessNum = _uiState.value.userGuessNum + 1,
            guessedCorrectWord = _uiState.value.userGuess == _uiState.value.word,
            wonGame = _uiState.value.guessedCorrectWord
        )

        if (_uiState.value.userGuessNum == _uiState.value.numOfGuesses) {
            // if they used up all their guesses and didn't succeed, they lost game
            _uiState.value = _uiState.value.copy(lostGame = true)
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun WordGuessingGame() {
    WordGuessingGameTheme {
        App()
    }
}