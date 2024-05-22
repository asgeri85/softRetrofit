package net.asgeri.softretrofit

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){
    GlobalScope.launch {
        println("Salam")
        delay(5000)
    }
    println("Sağol")
}