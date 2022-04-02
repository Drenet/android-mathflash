package com.awesomegroupllc.flash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.awesomegroupllc.base.util.AdManager
import com.awesomegroupllc.base.util.AdManager.loadAds
import com.awesomegroupllc.base.util.PlayManager.validateGamesSignInStatus
import com.awesomegroupllc.flash.ui.mvi.GameViewModel
import com.awesomegroupllc.flash.util.FlashPrefs.playGamesPlayerId
import com.awesomegroupllc.flash.R

/**
 * Main Activity, handles the NavGraph and the GameScreen in fragments
 */
class FlashActivity :
    AppCompatActivity(com.awesomegroupllc.base.R.layout.simple_navgraph_activity_fullscreen)
{

    private val gameViewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(com.awesomegroupllc.base.R.id.host) as NavHostFragment
        navHostFragment.navController.setGraph(R.navigation.navigation_flash)

        loadAds(AdManager.AdType.INTERSTITIAL, AdManager.AdType.REWARDED)
    }

    override fun onResume() {
        super.onResume()

        validateGamesSignInStatus {
            playGamesPlayerId = it
        }
    }
}