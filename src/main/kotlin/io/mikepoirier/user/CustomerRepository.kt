package io.mikepoirier.user

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : ReactiveMongoRepository<Customer, String>