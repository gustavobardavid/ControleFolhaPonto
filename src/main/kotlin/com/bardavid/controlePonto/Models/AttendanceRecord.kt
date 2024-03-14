package com.bardavid.controlePonto.Models

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class AttendanceRecord (
    @Id
    @GeneratedValue
    val id: Long?=null,
    var checkIn: String,
    val checkOut: LocalDateTime?= null,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    var employee: Employee
)