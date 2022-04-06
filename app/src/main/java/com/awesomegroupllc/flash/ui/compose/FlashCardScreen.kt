package com.awesomegroupllc.flash.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.awesomegroupllc.flash.model.Operand
import com.awesomegroupllc.flash.ui.data.Card
import com.awesomegroupllc.flash.ui.theme.FlashTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FlashCardScreen(
    card: Card,
    streak: Int = 0,
    incorrect: List<Int> = emptyList(),
    submitAnswer: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Streak: $streak", modifier = Modifier.fillMaxWidth())

        Box(modifier = Modifier.weight(1f)) {

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = card.value1.toString(),
                    fontSize = 128.sp
                )

                Text(
                    text = when (card.operand) {
                        Operand.ADDITION -> "+"
                        Operand.SUBTRACTION -> "-"
                        Operand.MULTIPLICATION -> "x"
                        Operand.DIVISION -> "÷"
                    },
                    fontSize = 78.sp
                )

                Text(
                    text = card.value2.toString(),
                    fontSize = 128.sp
                )
            }
        }

        if (card.availableAnswers.isNotEmpty()) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.Center
            ) {
                items(card.availableAnswers) {
                    Box(
                        modifier = Modifier.padding(top = 24.dp, start = 8.dp, end = 8.dp)
                    ) {
                        Button(
                            modifier = Modifier
                            .width(150.dp)
                            .height(72.dp)
                            .align(Alignment.Center),
                            shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp)),
                            onClick = { submitAnswer(it) },
                            enabled = !incorrect.contains(it)
                        ) {
                            Text(it.toString(), fontSize = 24.sp)
                        }
                    }
                }
            }
        } else {
            // TEXT INPUT???
        }
    }
}

@Preview
@Composable
fun previewMultiplyWithOptions() {
    FlashTheme {
        FlashCardScreen(
            Card(
                6,
                33,
                Operand.MULTIPLICATION,
                198,
                listOf(3000, 6234, 9, 100, 200, 198)
            )
        ) {}
    }
}

@Preview
@Composable
fun previewDivideWithOptions() {
    FlashTheme {
        FlashCardScreen(
            Card(
                30,
                5,
                Operand.DIVISION,
                198,
                listOf(3000, 6, 9)
            )
        ) {}
    }
}

