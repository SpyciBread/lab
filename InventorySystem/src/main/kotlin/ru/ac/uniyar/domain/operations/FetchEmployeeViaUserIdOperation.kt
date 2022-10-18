package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.Storage
import java.util.*
//8
class FetchEmployeeViaUserIdOperation(
    storage: Storage
) {
    private val employeeRepository = storage.employeeRepository

    operator fun invoke(token: String): Employee? {
        val uuid = try{
            UUID.fromString(token)
        } catch(_: IllegalArgumentException){
            return null
        }
        return employeeRepository.fetch(uuid)
    }
}