package com.bardavid.controlePonto.Services

import com.bardavid.controlePonto.Models.AttendanceRecord
import com.bardavid.controlePonto.Models.Employee
import com.bardavid.controlePonto.Repositories.EmployeeRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class EmployeeService (private val employeeRepository: EmployeeRepository){

    fun addEmployee(employee: Employee): Employee {
        return employeeRepository.save(employee)
    }

    fun getEmployeeById(id: Long): Employee {
        return employeeRepository.findById(id).orElseThrow { EntityNotFoundException("Employee not found") }
    }

    fun getAllEmployees() : List<Employee> {
        return employeeRepository.findAll().toList()
    }

    fun addAttendanceRecord(employeeId: Long): Employee {
        val employee = employeeRepository.findById(employeeId).orElseThrow { IllegalArgumentException("Employee not found") }
        val attendanceRecord = AttendanceRecord(
            checkIn = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
            employee = employee
        )
        employee.attendanceRecords.add(attendanceRecord)
        return employeeRepository.save(employee)
    }

    fun editAttendanceRecord(employeeId: Long) : Employee {
        val employee = employeeRepository.findById(employeeId).orElseThrow { IllegalArgumentException("Employee not found") }
        val lastRecord = employee.attendanceRecords.lastOrNull()
        if (lastRecord != null) {
            if (lastRecord.checkOut != null) {
                throw IllegalStateException("Last attendance record already has a check-out time")
            } else {
                lastRecord.checkOut = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            }
        } else {
            throw IllegalStateException("Last attendance record not exists")
        }
        return employeeRepository.save(employee)
    }

    fun deleteEmployee(id: Long) {
        return employeeRepository.deleteById(id)
    }
}