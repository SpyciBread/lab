package ru.ac.uniyar.web

import org.http4k.core.*
import org.http4k.lens.*
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.template.ViewModel
import ru.ac.uniyar.domain.operations.ListEmployeesOperationImplementation
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.EmployeeRepository
import ru.ac.uniyar.web.handlers.HandlerStorage
//import ru.ac.uniyar.web.handlers.NewHandler
import ru.ac.uniyar.web.models.NewViewModel
import ru.ac.uniyar.web.templates.ContextAwareViewRender
import java.util.*

fun formRouter(
    handlerStorage: HandlerStorage, htmlView: ContextAwareViewRender, employeeRepository: EmployeeRepository
) = routes(
    "/" bind Method.GET to handlerStorage.showStartPageHandler,

    "/login" bind Method.GET to handlerStorage.showLoginFormHandler,
    "/login" bind Method.POST to handlerStorage.authenticateUser,
    "/logout" bind Method.GET to handlerStorage.logOutUser,

    "/employees" bind Method.GET to handlerStorage.showEmployeesHandler,
    "/employees/{id}" bind Method.GET to handlerStorage.showEmployeeHandler,
    "/equipment" bind Method.GET to handlerStorage.showEquipmentListHandler,
    "/equipment/{id}" bind Method.GET to handlerStorage.showEquipmentHandler,
    "/ping" bind Method.GET to { Response(Status.OK) },

    //"/new" bind Method.GET to handlerStorage.newShow,

    //"/new" bind Method.GET to inventoryNew(htmlView),
    //"/new" bind Method.POST to addNew(htmlView, employeeRepository),
   //"/employees/{id}/edit" bind Method.GET to handlerStorage.editEmployeeHandler,
    //"/employees/{id}/edit" bind Method.POST to handlerStorage.applyEditEmployeeHandler
)


fun inventoryNew(htmlView: ContextAwareViewRender): HttpHandler={ request ->
    val model = NewViewModel()
    Response(Status.OK).with(htmlView(request) of model)
}

/*fun addNew(htmlView: ContextAwareViewRender, employeeRepository: EmployeeRepository): HttpHandler = { request ->
    val id = UUID.randomUUID()
    val name = FormField.string().required("name")
    val login = FormField.string().required("login")
    val phone = FormField.string().required("phone")

    val infoFromLens = Body.webForm(
        Validator.Feedback,
        name,
        login,
        phone
    ).toLens()

    val webForm = infoFromLens(request)
    if(webForm.errors.isEmpty() && name(webForm) != "" && login(webForm) != "" && phone(webForm) != ""){
        employeeRepository.add(id,Employee(
            id,
            name(webForm),
            login(webForm),
            phone(webForm)
        ))
        Response(Status.FOUND).header("Location","/employees")
    }
    else
        Response(Status.OK).with(htmlView(request) of NewViewModel(webForm))
}*/
