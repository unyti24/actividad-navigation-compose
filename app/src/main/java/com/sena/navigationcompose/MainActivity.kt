package com.sena.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.sena.navigationcompose.navigation.AppNavigation
import com.sena.navigationcompose.ui.theme.NavigationComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super .onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            NavigationComposeTheme {

                AppNavigation()

            }
        }
    }
}