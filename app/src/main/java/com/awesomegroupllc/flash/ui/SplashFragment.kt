package com.awesomegroupllc.flash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.awesomegroupllc.flash.R
import com.awesomegroupllc.flash.ui.mvi.GameViewModel
import com.awesomegroupllc.flash.ui.theme.FlashTheme
import kotlinx.coroutines.delay

class SplashFragment : Fragment() {

    // We do this here so the data preloads
    private val viewModel by activityViewModels<GameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            FlashTheme {
                Box(modifier = Modifier.fillMaxSize()){
                    Image(painter = painterResource(id = R.drawable.bg_main), contentDescription = "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = com.awesomegroupllc.base.R.drawable.ag_logo_white),
                        contentDescription = "Logo",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            delay(2000)
            findNavController().navigate(R.id.action_loadGame)
        }
    }

}