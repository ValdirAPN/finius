package com.finius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.finius.features.home.presentation.HomeScreen
import com.finius.ui.theme.FiniusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiniusTheme {
                Navigator(
                    HomeScreen
                )
            }
        }
    }
}
