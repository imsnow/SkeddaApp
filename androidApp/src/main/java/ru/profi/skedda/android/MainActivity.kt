package ru.profi.skedda.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import org.koin.android.ext.android.inject
import ru.profi.skedda.shared.router.Router

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    val router: Router by inject()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (router as ComposeRouter).initWithActivity(this)
    }
}
