package ru.ac.uniyar.domain.storage

import java.util.UUID

class EmployeeRepository(employees: List<Employee>) {
    private val employees = employees.associateBy { it.id }.toMutableMap()

    fun list() = employees.values.toList()

    fun fetch(id: UUID) = employees[id]

    fun add(id: UUID,employee: Employee){
        employees[id] = employee
    }
    fun edit(employee: Employee) {
        employees[employee.id] = employee
    }
}
