package io.mikepoirier

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinReactiveWebApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinReactiveWebApplication::class.java, *args)
}
