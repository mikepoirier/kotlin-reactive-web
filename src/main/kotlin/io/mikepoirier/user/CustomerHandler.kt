package io.mikepoirier.user

import io.mikepoirier.json
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import java.util.*

@Component
class CustomerHandler(val customerRepo: CustomerRepository,
                      val errorHandler: ErrorHandler) {

    private val customer_id_path_variable = "id"

    fun saveUser(request: ServerRequest) = ok()
        .json()
        .body(customerRepo.saveAll(
            request.bodyToMono<Customer>()
                .map { customer -> customer.copy(id = UUID.randomUUID().toString()) }
        ).last())

    fun getUser(request: ServerRequest): Mono<ServerResponse> {
        val resource = request.pathVariable(customer_id_path_variable)
        return Mono.just(resource)
            .flatMap { customerRepo.findById(it) }
            .flatMap { ok().json().body(Mono.just(it)) }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).body(errorHandler.notFound(resource)))
    }

    fun getAllCustomers(request: ServerRequest) = ok()
        .json()
        .body(customerRepo.findAll())

    fun deleteCustomer(request: ServerRequest) = ok()
        .json()
        .body(customerRepo.deleteById(request.pathVariable(customer_id_path_variable)))
}

data class Error(val message: String)

@Component
class ErrorHandler() {
    fun notFound(resource: String) = Mono.just(Error("The following resource could not be found: $resource"))
}
