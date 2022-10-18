package ru.ac.uniyar.web.filters

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.with
import org.http4k.lens.RequestContextLens
import ru.ac.uniyar.domain.operations.FetchPermissionsViaIdOperation
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.RolePermissions


fun authorizationFilter(
    currentEmployee: RequestContextLens<Employee?>,
    permissionsLens: RequestContextLens<RolePermissions>,
    fetchPermissionsViaIdQuery: FetchPermissionsViaIdOperation,
): Filter = Filter { next: HttpHandler ->
    { request: Request ->
        val permissions = currentEmployee(request)?.let {
            fetchPermissionsViaIdQuery(it.roleId)
        } ?: RolePermissions.ANONYMOUS_ROLE
        val authorizedRequest = request.with(permissionsLens of permissions)
        next(authorizedRequest)
    }

}