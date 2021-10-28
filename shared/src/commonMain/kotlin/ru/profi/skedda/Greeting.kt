package ru.profi.skedda

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}