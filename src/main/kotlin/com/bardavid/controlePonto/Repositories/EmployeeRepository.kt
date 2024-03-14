package com.bardavid.controlePonto.Repositories

import com.bardavid.controlePonto.Models.Employee
import org.springframework.data.repository.CrudRepository

interface EmployeeRepository: CrudRepository<Employee, Long>