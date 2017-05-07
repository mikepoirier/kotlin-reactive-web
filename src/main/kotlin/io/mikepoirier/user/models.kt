package io.mikepoirier.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Customer(val firstName: String = "", val lastName: String = "", @Id val id: String = "")