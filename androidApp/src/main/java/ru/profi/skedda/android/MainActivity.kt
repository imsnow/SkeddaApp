package ru.profi.shared.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }

//        val tv: TextView = findViewById(R.id.text_view)
//        tv.text = greet()
//
//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            val api = SkeddaApi()
//            GlobalScope.launch {
//                api.login(
//                    email = "miha_mai@mail.ru",
//                    password = "hui"
//                )
//                api.webs()
//            }
//
//        }
    }
}
