package com.awesomegroupllc.flash.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.awesomegroupllc.base.ui.AwesomeShapes
import com.awesomegroupllc.base.ui.AwesomeTypography

/**
 * secondaryVariant - Switches (ON)
 * onSurface - Switch Track (OFF)
 * surface - Switch Knob (OFF)
 */

@Composable
fun FlashTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors =
        if (isSystemInDarkTheme())
            darkColors().copy(
                primary = Color(0xFFDD0000),
                primaryVariant = Color(0xFFBC0C0C),
                onPrimary = Color.White, // Dropdown background, Button text
                secondary = Color(0xFF3F3F3F), //; MODS - Dropdown BG
                onSecondary = Color(0xFFEEEEEE),
                secondaryVariant = Color(0xFFBC0C0C),
                surface = Color.DarkGray,   // Switch Knob (Off)
                onSurface = Color.White, // Switch Track (off), Text on dropdown
                background = Color(0xAA111111),
                onBackground = Color.White,
                // What are these?
//                error = Color.DarkGray,
//                onError = Color.Blue
            )
        else
            lightColors().copy(
            primary = Color(0xFFBC0C0C),
            primaryVariant = Color(0xFFDD0000),
            onPrimary = Color.White,
            secondary = Color.White, //; MODS - Dropdown BG
            onSecondary = Color(0xFF111111),
            secondaryVariant = Color(0xFFBC0C0C),    // Switches - ON
            surface = Color.DarkGray,  // Switch Knob (Off)
//            onSurface = Color.White, // Switch Track (off)
//            background = Color(0xAA111111),
//            onBackground = Color.White,
//            // What are these?
//            error = Color.DarkGray,
//            onError = Color.Blue
        ),
        typography = AwesomeTypography,
        shapes = AwesomeShapes
    ){
        Surface(
            color = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground
        ) {
            content()
        }
    }
}