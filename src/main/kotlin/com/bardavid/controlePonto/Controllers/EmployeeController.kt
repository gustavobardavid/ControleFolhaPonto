package com.bardavid.controlePonto.Controllers

import com.bardavid.controlePonto.Models.Employee
import com.bardavid.controlePonto.Services.EmployeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("funcionarios")
class EmployeeController (private val employeeService: EmployeeService){

    @GetMapping
    fun getAllEmployees() : ResponseEntity<List<Employee>> {
        val employees = employeeService.getAllEmployees()
        return ResponseEntity.ok(employees)
    }

    @PostMapping
    fun addEmployee(@RequestBody employee: Employee): ResponseEntity<Employee>{
       val addedEmployee = employeeService.addEmployee(employee)
        return ResponseEntity.ok(addedEmployee)
    }

    @GetMapping("{id}")
    fun getEmployeeById(@PathVariable id: Long): ResponseEntity<Employee> {
        val employee = employeeService.getEmployeeById(id)
        return ResponseEntity.ok(employee)
    }

    @PostMapping("{id}/checkin")
    fun addAttendanceRecord(@PathVariable id: Long): ResponseEntity<Employee> {
        val employee = employeeService.addAttendanceRecord(id)
        return ResponseEntity.ok(employee)
    }

    @PostMapping("{id}/checkout")
    fun editAttendanceRecord (@PathVariable id: Long): ResponseEntity<Employee> {
        val employee = employeeService.editAttendanceRecord(id)
        return ResponseEntity.ok(employee)
    }
    @DeleteMapping("{id}")
    fun deleteEmployee (@PathVariable id: Long): ResponseEntity<Unit> {
        val employee = employeeService.deleteEmployee(id)
        return ResponseEntity.ok(employee)
    }
}