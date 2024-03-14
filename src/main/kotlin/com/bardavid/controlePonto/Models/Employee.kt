package com.bardavid.controlePonto.Models

import jakarta.persistence.*

@Entity
data class Employee (
        @Id
        @GeneratedValue
        val id: Long,
        val cpf: String = "",
        val name: String = "",

        @OneToMany(mappedBy = "employee", cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
        val attendanceRecords: MutableList<AttendanceRecord> = mutableListOf()
)