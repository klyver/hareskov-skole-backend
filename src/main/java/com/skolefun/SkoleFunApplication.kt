package com.skolefun

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer

@SpringBootApplication
class DemoApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}
