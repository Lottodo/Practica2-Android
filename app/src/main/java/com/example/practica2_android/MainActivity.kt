package com.example.practica2_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practica2_android.ui.theme.Practica2AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practica2AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuestionGUI()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun QuestionGUI() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Pregunta de testeo, sin valor real aparentemente",
            style = MaterialTheme. typography.headlineLarge
        )

        Spacer(modifier = Modifier.padding(16.dp),)

        OutlinedButton(
            onClick = {TODO()},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Respuesta 1")
        }
        OutlinedButton(
            onClick = {TODO()},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text="Respuesta 2")
        }
        OutlinedButton(
            onClick = {TODO()},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Respuesta 3")
        }
        OutlinedButton(
            onClick = {TODO()},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Respuesta 4")
        }
    }
}