package com.awesomegroupllc.flash.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.awesomegroupllc.base.util.set
import com.awesomegroupllc.flash.model.Difficulty
import com.awesomegroupllc.flash.model.Level
import com.awesomegroupllc.flash.model.Operand

object FlashPrefs {

    const val MIN_WORD_LENGTH = 2

    fun init(app: Application) {
        sharedPrefs =
            app.getSharedPreferences("com.awesomegroupllc.flash", Context.MODE_PRIVATE)
    }

    private lateinit var sharedPrefs: SharedPreferences

    var difficulty: Difficulty
        get() = Difficulty.valueOf(sharedPrefs.getString("DIFFICULTY", "EASY") ?: "EASY")
        set(value) = sharedPrefs.set {
            it.putString("DIFFICULTY", value.name)
        }

    var operand: Operand
        get() {
            val op = Operand.valueOf(sharedPrefs.getString("OP", "ADDITION") ?: "ADDITION")
//            when(op){
//            }
            return op
        }
        set(value) = sharedPrefs.set {
            it.putString("OP", value.name)
        }

    var level: Level
        get() = Level.valueOf(sharedPrefs.getString("LVL", "ONE") ?: "ONE")
        set(value) = sharedPrefs.set {
            it.putString("LVL", value.name)
        }

    var streak: Int
        get() = sharedPrefs.getInt("STREAK", 0)
        set(value) {
            sharedPrefs.set {
                it.putInt("STREAK", value)
            }
        }

    var points: Int
        get() = sharedPrefs.getInt("POINTS", 0)
        set(value) = sharedPrefs.set {
            it.putInt("POINTS", value)
        }

    // This should only be changed by SETTINGS
    var themeName: String?
        get() = sharedPrefs.getString("THEME", "BASE")
        set(value) = sharedPrefs.set {
            it.putString("THEME", value)
        }

    // Session Var - NOT STORED
    var playGamesPlayerId: String? = null
}