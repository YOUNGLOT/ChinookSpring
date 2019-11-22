package com.example.chinookspring

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChinookspringApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("hello world!")
    }
}

fun main(args: Array<String>) {
    runApplication<ChinookspringApplication>(*args)
}
