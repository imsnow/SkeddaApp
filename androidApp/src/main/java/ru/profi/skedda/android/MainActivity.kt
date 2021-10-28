package ru.profi.skedda.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.profi.skedda.Greeting
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.profi.skedda.network.SkeddaApi
import kotlin.coroutines.CoroutineContext

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val api = SkeddaApi()
            GlobalScope.launch {
                val result = api.login(
                    email = "IgnatovMV@profi.ru",
                    password = "hui"
                )
            }

        }
    }
}
