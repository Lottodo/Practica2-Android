package com.example.practica2_android

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    val openDialogCustom = remember{ mutableStateOf(false) }

    if (ronda>=6){
        openDialogCustom.value = true
        flag = false
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
        GameQuestionCard(modifier, rondaString, questionString)

        Spacer(modifier = Modifier.padding(16.dp))

        GameButton(isRed = game.checkQuestion(0),
            onClicky = { ronda += 1; if(game.checkQuestion(0)) puntos += 1 },
            buttonString = res1String)

        Spacer(modifier = Modifier.padding(8.dp))

        GameButton(isRed = game.checkQuestion(0),
            onClicky = { ronda += 1; if(game.checkQuestion(1)) puntos += 1 },
            buttonString = res2String)

        Spacer(modifier = Modifier.padding(8.dp))

        GameButton(isRed = game.checkQuestion(0),
            onClicky = { ronda += 1; if(game.checkQuestion(2)) puntos += 1 },
            buttonString = res3String)

        Spacer(modifier = Modifier.padding(8.dp))

        GameButton(isRed = game.checkQuestion(0),
            onClicky = { ronda += 1; if(game.checkQuestion(3)) puntos += 1 },
            buttonString = res4String)
    }

    if (openDialogCustom.value) {
        CustomDialog(openDialogCustom = openDialogCustom,
            restartOnClick = {
                game.restartGame()
                flag = false
                ronda = 0
            },
            finishOnClick = {
                val activity = (context as? Activity)
                activity?.finish()
            },
            puntos
        )

    }
}

@Composable
fun GameButton(onClicky: () -> Unit,
               buttonString: String,
               isRed: Boolean) {
    val defaultColor = MaterialTheme.colorScheme.primary
    var buttonColor by remember { mutableStateOf(Color.Blue) }
    var buttonText by remember { mutableStateOf("Click me") }
    var isEnabled by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    Button(onClick = {
            isEnabled = false
            buttonText = "Processing..."
            buttonColor = if (isRed) Color.Red else Color.Blue

            // Freeze for 2 seconds
            coroutineScope.launch {
                delay(2000)
                buttonColor = defaultColor // Sets back to the default color
                buttonText = "Click me"
                isEnabled = true
            }
            onClicky()
        },

        enabled = isEnabled
    ) {
        Text(
            text = buttonString,
            fontSize = 20.sp,
            color = Color(0xFF9C27B0)
        )
    }
}

data class ColorHolder(val color: Color)

@Composable
fun GameQuestionCard(modifier: Modifier, rondaString: String, questionString: String) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(15.dp),
        //border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(4.dp)
            .wrapContentSize(Alignment.Center)
            .height(200.dp)
            .width(375.dp)
    )
    {
        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ronda $rondaString",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .align(Alignment.Start)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            )
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = questionString,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}