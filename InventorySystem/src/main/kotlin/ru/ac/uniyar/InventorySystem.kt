package ru.ac.uniyar

import org.http4k.core.*
import org.http4k.filter.ServerFilters
import org.http4k.lens.RequestContextKey
import org.http4k.lens.RequestContextLens
import org.http4k.routing.ResourceLoader
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Undertow
import org.http4k.server.asServer
import org.http4k.template.viewModel
import ru.ac.uniyar.domain.operations.OperationStorage
import ru.ac.uniyar.domain.operations.computations.hashPassword
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.RolePermissions
import ru.ac.uniyar.domain.storage.Settings
import ru.ac.uniyar.domain.storage.initializeRepositoryStorage
import ru.ac.uniyar.web.filters.JwtTools
import ru.ac.uniyar.web.filters.ShowErrorMessageFilter
import ru.ac.uniyar.web.filters.authenticationFilter
import ru.ac.uniyar.web.filters.authorizationFilter
import ru.ac.uniyar.web.formRouter
import ru.ac.uniyar.web.handlers.HandlerStorage
import ru.ac.uniyar.web.templates.ContextAwarePebbleTemplates
import ru.ac.uniyar.web.templates.ContextAwareViewRender

fun main() {
    //hashPassword("","")
    val settings = Settings()
    val storage = initializeRepositoryStorage()
    val operationStorage = OperationStorage(storage, settings)
    val renderer = ContextAwarePebbleTemplates().HotReload("src/main/resources")
    val htmlView = ContextAwareViewRender.withContentType(renderer, ContentType.TEXT_HTML)
    val contexts = RequestContexts()
    val currentEmployeeLens: RequestContextLens<Employee?> = RequestContextKey.optional(contexts, "employee")
    val permissionsLens: RequestContextLens<RolePermissions> = RequestContextKey.required(contexts, "permissions")

    htmlView.associateContextLens("currentEmployee", currentEmployeeLens)
    htmlView.associateContextLens("permissions", permissionsLens)
    val jwtTools = JwtTools(settings.getSalt(),"ru.ac.uniyar.InventorySystem")

    val handlerStorage = HandlerStorage(
        currentEmployeeLens,
        permissionsLens,
        operationStorage,
        htmlView,
        jwtTools)

    val dynamicRouter = formRouter(handlerStorage, htmlView, storage.employeeRepository)
    val staticFilesHandler = static(ResourceLoader.Classpath("/ru/ac/uniyar/public"))
    val routes = routes(
        dynamicRouter,
        staticFilesHandler
    )

    val authorizedApp = authenticationFilter(
        currentEmployeeLens,
        operationStorage.fetchEmployeeViaUserIdOperation,
        jwtTools
    ).then(
        authorizationFilter(
            currentEmployeeLens,
            permissionsLens,
            operationStorage.fetchPermissionsViaIdOperation,
        ).then(routes)
    )

    val app = routes(
        authorizedApp,
        staticFilesHandler
    )


    val printingApp: HttpHandler =
        ServerFilters.InitialiseRequestContext(contexts)
            .then(ShowErrorMessageFilter(htmlView))
            .then(app)
    //val app: HttpHandler = ShowErrorMessageFilter(htmlView)
      //  .then(routes)

    val server = printingApp.asServer(Undertow(9000)).start()
    println("Server started on http://localhost:" + server.port())
}
