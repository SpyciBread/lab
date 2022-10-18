package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.lens.*
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.template.ViewModel
import ru.ac.uniyar.domain.operations.ListEmployeesOperation
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.EmployeeRepository
import ru.ac.uniyar.web.models.NewViewModel
import ru.ac.uniyar.web.models.ShowEmployeesVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender
import java.util.*

/*class NewHandler (
    private val htmlView: ContextAwareViewRender
    ) : HttpHandler {

        override fun invoke(request: Request): Response {
            val model = NewViewModel()

            return Response(Status.OK).with(htmlView(request) of model)
        }


    fun newCreationRoute(htmlView: ContextAwareViewRender) = routes(
        "/" bind Method.GET to inventoryNew(htmlView),
        "/" bind Method.POST to addNew(htmlView))

    fun inventoryNew(htmlView: ContextAwareViewRender): HttpHandler={ request ->
        val model = NewViewModel()
        Response(Status.OK).with(htmlView(request) of model)
    }

   fun addNew(htmlView: ContextAwareViewRender): HttpHandler = { request ->
       val id = FormField.string().required("id")
       val name = FormField.string().required("name")
       val login = FormField.string().required("login")
       val phone = FormField.string().required("phone")

       val infoFromLens = Body.webForm(
           Validator.Feedback,
           id,
           name,
           login,
           phone
       ).toLens()

        val webForm = infoFromLens(request)
        if(webForm.errors.isEmpty()){
                EmployeeRepository(listOf(
                    Employee(
                        UUID.fromString(id(webForm)),
                        name(webForm),
                        login(webForm),
                        phone(webForm)
                    )
            ))
            Response(Status.FOUND).header("Location","/employees")
        }
       else
            Response(Status.OK).with(htmlView(request) of NewViewModel(webForm))
   }


}*/
