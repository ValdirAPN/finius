package com.finius

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.features.home.presentation.HomeScreen

class SplashScreen : Screen {
    @Composable
    override fun Content() {

        val model = rememberScreenModel<SplashScreenModel>()

        val strings = strings.categoriesStrings.defaultCategories
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            model.initialize(
                defaultCategoriesStrings = strings,
                onSuccess = { navigator.replace(HomeScreen()) }
            )
        }

        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}