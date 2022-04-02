package com.awesomegroupllc.flash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
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

                val state = viewModel.cardLiveData.observeAsState(Card(0, 0, Operand.ADDITION, 0))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = SpaceAround
                ) {

                    Text(text = state.value.value1.toString())
                    Text(
                        text = when (state.value.operand) {
                            Operand.ADDITION -> "+"
                            Operand.SUBTRACTION -> "-"
                            else -> "???"
                        }
                    )
                    Text(text = state.value.value2.toString())

                    LazyVerticalGrid(cells = GridCells.Fixed(3)){
                        items(state.value.availableAnswers){
                            AwesomeButton(text = it.toString()) {
                                viewModel.submitAnswer(state.value, it)
                            }
                        }
                    }
                }

            }
        }
    }
}