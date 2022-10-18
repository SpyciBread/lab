package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.lens.RequestContextLens
import ru.ac.uniyar.domain.operations.OperationStorage
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.RolePermissions
import ru.ac.uniyar.web.filters.JwtTools
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class HandlerStorage(
    currentEmployeeLens: RequestContextLens<Employee?>,
    permissionsLens: RequestContextLens<RolePermissions>,
    operationStorage: OperationStorage,
    htmlView: ContextAwareViewRender,
    jwtTools: JwtTools
) {
    val showEmployeesHandler: HttpHandler = ShowEmployeesHandler(
        permissionsLens,
        operationStorage.listEmployeesOperation,
        htmlView,
    )

    val showLoginFormHandler = ShowLoginFormHandler(
        htmlView,
    )

    val authenticateUser = AuthenticateUser(
        operationStorage.authenticateUserViaLoginQuery,
        htmlView,
        jwtTools
    )

    val logOutUser = LogOutUser()

    val showEmployeeHandler: HttpHandler = ShowEmployeeHandler(
        permissionsLens,
        operationStorage.fetchEmployeeOperation,
        htmlView,
    )

    val showEquipmentHandler: HttpHandler = ShowEquipmentHandler(
        permissionsLens,
        operationStorage.fetchEquipmentOperation,
        htmlView,
    )

    val showEquipmentListHandler: HttpHandler = ShowEquipmentListHandler(
        permissionsLens,
        operationStorage.listEquipmentOperation,
        htmlView,
    )

    val showStartPageHandler: HttpHandler = ShowStartPageHandler(
        htmlView,
    )

    /*val newShow: HttpHandler = NewHandler(
        htmlView,
    )*/

    /*val applyEditEmployeeHandler: HttpHandler = ApplyEditEmployeeHandler(
        operationStorage.fetchEmployeeOperation,
        operationStorage.editEmployeeOperation,
        htmlView
    )
    val editEmployeeHandler: HttpHandler = EditFormEmployeeHandler(
        operationStorage.fetchEmployeeOperation,
        htmlView
    )*/
}
