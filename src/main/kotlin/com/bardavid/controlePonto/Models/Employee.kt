package com.bardavid.controlePonto.Models

import com.bardavid.controlePonto.Interfaces.Calculable
import jakarta.persistence.*
import java.time.Duration

@Entity
data class Employee (
        @Id
        @GeneratedValue
        val id: Long,
        val cpf: String = "",
        val name: String = "",

        @OneToMany(mappedBy = "employee", cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
        val attendanceRecords: MutableList<AttendanceRecord> = mutableListOf(),
) : Calculable {
        override fun calculateHours(): Double {
                var totalHours = 0.0
                for (record in attendanceRecords) {
                        val checkIn = record.checkIn
                        val checkOut = record.checkOut
                        if (checkOut != null) {
                                val duration = Duration.between(checkIn, checkOut)
                                totalHours += duration.toMinutes().toDouble() / 60 // Converter minutos para horas
                        }
                }
                return totalHours
        }
}