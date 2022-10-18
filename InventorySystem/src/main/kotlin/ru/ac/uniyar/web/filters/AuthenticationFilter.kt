package ru.ac.uniyar.web.filters

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.cookie.cookie
import org.http4k.core.with
import org.http4k.lens.RequestContextLens
import ru.ac.uniyar.domain.operations.FetchEmployeeViaUserIdOperation
import ru.ac.uniyar.domain.storage.Employee
//филтры копируем (6)
fun authenticationFilter(
    currentEmployee: RequestContextLens<Employee?>,
    fetchEmployeeViaUserId: FetchEmployeeViaUserIdOperation,
    jwtTools: JwtTools,
): Filter = Filter { next: HttpHandler ->
    {   request: Request ->
        val requestWithEmployee = request.cookie("token")?.value?.let { token ->
            jwtTools.subject(token)
        }?.let { userId ->
            fetchEmployeeViaUserId(userId)
        }?.let { employee ->
            request.with(currentEmployee of employee)
        } ?: request
        next(requestWithEmployee)
    }
}