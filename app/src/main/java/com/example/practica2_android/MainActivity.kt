package com.example.practica2_android

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.Visibility
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.practica2_android.ui.theme.Practica2AndroidTheme
import java.io.InputStream
import androidx.compose.material3.TextButton as TextButton1


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
                    QuestionApp(this)
                }
            }
        }
    }
}


@Composable
fun QuestionApp(context : Context) {
    QuestionGame(
        context,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun QuestionGame(context : Context, modifier : Modifier = Modifier) {
    var ronda by remember { mutableIntStateOf(0) }
    var puntos by remember { mutableIntStateOf(0) }
    var flag = true

    //Debug
    val toastS = Toast.makeText(LocalContext.current,"Salir",Toast.LENGTH_SHORT)
    val toastR = Toast.makeText(LocalContext.current,"Reiniciar",Toast.LENGTH_SHORT)


    if (ronda>=6){
        flag = false

        DialogWithOptions(onDismissRequest = { toastS.show() },
            onConfirmation = { toastR.show() }, puntos)
    }

    val game = Game(context)

    val questionList = game.doQuestion(ronda)
    val rondaString = questionList[0]
    val questionString = questionList[1]
    val res1String = questionList[2]
    val res2String = questionList[3]
    val res3String = questionList[4]
    val res4String = questionList[5]


    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .alpha(if (!flag) 0f else 1f),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Ronda $rondaString",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(15.dp),
            //border = BorderStroke(2.dp, Color.Black),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .padding(4.dp)
                .wrapContentSize(Alignment.Center)
                .height(150.dp)
                .width(375.dp)
        )
        {
            Column (
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = questionString,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))
        GameButton(onClicky = { ronda += 1; if(game.checkQuestion(0)) puntos += 1 }, buttonString = res1String)
        Spacer(modifier = Modifier.padding(8.dp))
        GameButton(onClicky = { ronda += 1; if(game.checkQuestion(1)) puntos += 1 }, buttonString = res2String)
        Spacer(modifier = Modifier.padding(8.dp))
        GameButton(onClicky = { ronda += 1; if(game.checkQuestion(2)) puntos += 1 }, buttonString = res3String)
        Spacer(modifier = Modifier.padding(8.dp))
        GameButton(onClicky = { ronda += 1; if(game.checkQuestion(3)) puntos += 1 }, buttonString = res4String)
    }
}

@Composable
fun GameButton(onClicky: () -> Unit,
               buttonString: String, ) {
    OutlinedButton(
        onClick = { onClicky }
    ) {
        Text(
            text = buttonString,
            fontSize = 20.sp
        )
    }
}

@Composable
fun DialogWithOptions(onDismissRequest: () -> Unit,
                      onConfirmation: () -> Unit,
           puntos: Int) {

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Fin del juego",
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp
                )
                Text(
                    text = "Puntaje: $puntos",
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton1(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Salir")
                    }
                    TextButton1(
                        onClick = { onConfirmation()

                                  },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Volver a jugar")
                    }
                }
            }
        }
    }
}