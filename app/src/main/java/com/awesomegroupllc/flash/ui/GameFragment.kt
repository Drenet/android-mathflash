package com.awesomegroupllc.flash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.awesomegroupllc.base.ui.compose.AwesomeButton
import com.awesomegroupllc.flash.model.Operand
import com.awesomegroupllc.flash.ui.data.Card
import com.awesomegroupllc.flash.ui.mvi.GameViewModel
import com.awesomegroupllc.flash.ui.theme.FlashTheme

class GameFragment : Fragment() {

    private val viewModel by activityViewModels<GameViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            FlashTheme {

                val state = viewModel.cardLiveData.observeAsState(
                    Card(0, 0, Operand.ADDITION, 0)
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally
                ) {

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = spacedBy(16.dp)
                    ) {
                        Text(
                            text = state.value.value1.toString(),
                            fontSize = 72.sp
                        )
                        if (state.value.operand == Operand.DIVISION) {
                            Divider(
                                modifier = Modifier.width(100.dp),
                                thickness = 3.dp,
                                color = MaterialTheme.colors.onBackground
                            )
                        } else {
                            Text(
                                text = when (state.value.operand) {
                                    Operand.ADDITION -> "+"
                                    Operand.SUBTRACTION -> "-"
                                    Operand.MULTIPLICATION -> "x"
                                    else -> "???"
                                },
                                fontSize = 52.sp
                            )
                        }

                        Text(
                            text = state.value.value2.toString(),
                            fontSize = 72.sp
                        )
                    }

                    if (state.value.availableAnswers.isNotEmpty()) {
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(3),
                            horizontalArrangement = Center,
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
                        ) {
                            items(state.value.availableAnswers) {
                                AwesomeButton(
                                    modifier = Modifier.size(56.dp),
                                    text = it.toString()
                                ) {
                                    viewModel.submitAnswer(state.value, it)
                                }
                            }
                        }
                    } else {
                        // TEXT INPUT???
                    }
                }
            }
        }
    }
}