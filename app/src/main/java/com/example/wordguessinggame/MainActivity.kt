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
    val word: List<String>, // maybe we want this to be string? or have 2 variables?
    val userGuess: String = "",
    val guessNum: Int = 0,
    val guessedCorrectWord: Boolean = false
)

class WordGameViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(WordGameState(
        word = listOf("f", "a", "r", "m")
    ))
    val uiState: StateFlow<WordGameState> = _uiState

    fun onGuess() {

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