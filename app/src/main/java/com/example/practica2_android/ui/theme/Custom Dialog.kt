package com.example.practica2_android.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun CustomDialog(openDialogCustom: MutableState<Boolean>) {
    Dialog(onDismissRequest = { openDialogCustom.value = false}) {
        CustomDialogUI(openDialogCustom = openDialogCustom)
    }
}

@Composable
fun CustomDialogUI(modifier: Modifier = Modifier, openDialogCustom: MutableState<Boolean>) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 30.dp
                )
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Headline",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Text from the box explaining stuff",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 25.dp,
                            end = 25.dp
                        )
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { openDialogCustom.value = false }) {
                    Text(
                        text = "Option A",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(top = 5.dp,bottom = 5.dp)
                    )
                }

                TextButton
            }
        }
    }

}