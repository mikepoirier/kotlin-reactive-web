package io.mikepoirier.config

import io.mikepoirier.user.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class RoutesConfig(val customerHandler: CustomerHandler) {

    @Bean
    fun apiRoutes() = router {
        (accept(MediaType.APPLICATION_JSON) and "/api").nest {
            "/customer".nest {
                GET("/", customerHandler::getAllCustomers)
                GET("/{id}", customerHandler::getUser)
                POST("/", customerHandler::saveUser)
                PUT("/{id}")
                DELETE("/{id}", customerHandler::deleteCustomer)
            }
        }
    }
}
