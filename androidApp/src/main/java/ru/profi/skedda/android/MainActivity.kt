package ru.profi.skedda.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import ru.profi.skedda.android.composables.LoginScreen
import ru.profi.skedda.android.composables.MainScreen

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            LoginScreen()
            MainScreen()
        }
    }
}
