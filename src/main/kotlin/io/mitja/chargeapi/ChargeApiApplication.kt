package io.mitja.chargeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChargeApiApplication

fun main(args: Array<String>) {
    runApplication<ChargeApiApplication>(*args)
}
