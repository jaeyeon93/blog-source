package me.jimmy.blogsource.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
internal class ApiApplication
fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}