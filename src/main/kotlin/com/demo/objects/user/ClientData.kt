package com.demo.objects.user

import java.time.LocalDate
import java.time.LocalDateTime

data class ClientData(
    val username: String,
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val gender: String,
    val phoneNumber: String,
    val dateOfBirth: LocalDate,
    val country: String,
    val aboutYourselfText: String
)