package ru.ac.uniyar.domain.operations


import ru.ac.uniyar.domain.operations.computations.hashPassword
import ru.ac.uniyar.domain.storage.Settings
import ru.ac.uniyar.domain.storage.Storage


class AuthenticateUserViaLoginQuery(
    storage: Storage,
    private val settings: Settings,
) {
    private val employeeRepository = storage.employeeRepository

    operator fun invoke(login: String, password: String) : String {
        val user = employeeRepository.list().find { it.login == login } ?: throw AuthenticationError()
        val hashedPassword = hashPassword(password, settings.getSalt())
        if (hashedPassword != user.password)
            throw AuthenticationError()
        return user.id.toString()
    }
}

class AuthenticationError : RuntimeException("User or password is wrong")