package com.awesomegroupllc.flash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.awesomegroupllc.flash.model.Operand
import com.awesomegroupllc.flash.ui.compose.FlashCardScreen
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

                val streak = viewModel.streak.observeAsState(0)

                val incorrect = viewModel.incorrectAnswersOnCard.observeAsState(emptyList())

                FlashCardScreen(state.value, streak.value, incorrect.value) {
                    viewModel.submitAnswer(state.value, it)
                }
            }
        }
    }
}