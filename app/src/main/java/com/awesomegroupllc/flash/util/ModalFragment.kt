package com.awesomegroupllc.flash.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.awesomegroupllc.flash.ui.theme.FlashTheme

class ModalFragment(
    val composable: @Composable () -> Unit
) : Fragment() {
    // Cheat here - don't destroy and recreate this fragment, just keep it
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            FlashTheme {
                composable()
            }
        }
    }
}