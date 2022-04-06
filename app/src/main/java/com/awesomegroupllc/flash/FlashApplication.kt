package com.awesomegroupllc.flash

import android.app.Application
import com.awesomegroupllc.base.util.Analytics
import com.awesomegroupllc.base.util.PlayManager.initializeGames
import com.awesomegroupllc.flash.util.FlashPrefs

class FlashApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeGames()

        FlashPrefs.init(this)
        Analytics.init(this)

        // Every app launch resets the streak
        FlashPrefs.streak = 0
    }

}