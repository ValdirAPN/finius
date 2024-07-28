package com.finius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import com.finius.ui.strings.Locales
import com.finius.ui.strings.Strings
import com.finius.ui.strings.StringsPt
import com.finius.ui.theme.FiniusTheme

class MainActivity : ComponentActivity() {

    private lateinit var lyricist: Lyricist<Strings>
    private lateinit var LocalCommonStrings: ProvidableCompositionLocal<Strings>

    @OptIn(ExperimentalVoyagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            lyricist = rememberStrings(
                translations = mapOf(
                    Locales.PT to StringsPt
                ),
                defaultLanguageTag = Locales.PT
            )

            LocalCommonStrings = staticCompositionLocalOf { StringsPt }

            FiniusTheme {
                ProvideStrings(lyricist = lyricist, provider = LocalCommonStrings) {
                    Navigator(
                        screen = SplashScreen(),
                        disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
                    ) { navigator ->
                        SlideTransition(
                            navigator = navigator,
                            disposeScreenAfterTransitionEnd = true
                        )
                    }
                }
            }
        }
    }
}
