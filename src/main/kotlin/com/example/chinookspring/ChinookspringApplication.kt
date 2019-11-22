package com.example.chinookspring

import com.example.chinookspring.repositories.Repository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChinookspringApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println(Repository.album.count())
    }
}

fun main(args: Array<String>) {
    runApplication<ChinookspringApplication>(*args)
}
