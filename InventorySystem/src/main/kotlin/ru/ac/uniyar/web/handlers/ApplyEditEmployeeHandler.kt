package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.lens.*
import ru.ac.uniyar.domain.operations.EditEmployeeOperation
import ru.ac.uniyar.domain.operations.FetchEmployeeOperation
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.web.models.EditFormEmployeeViewModel
import ru.ac.uniyar.web.templates.ContextAwareViewRender

/*class ApplyEditEmployeeHandler (
    private val fetchEmployeeOperation: FetchEmployeeOperation,
    private val editEmployeeOperation: EditEmployeeOperation,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    companion object {
        private val idLens = Path.uuid().of("id")
        private val nameLens = FormField.nonEmptyString().required("name")
        val loginLens = FormField.nonEmptyString().required("login")
        val phoneLens = FormField.nonEmptyString().required("phone")
        val formLens = Body.webForm(
            Validator.Feedback,
            nameLens,
            loginLens,
            phoneLens
        ).toLens()
    }

    override fun invoke(request: Request): Response {
        val employeeToEdit = fetchEmployeeOperation.fetch(idLens.invoke(request)) ?: return Response(Status.BAD_REQUEST)
        /*return Response(Status.OK).with(htmlView(request) of EditFormEmployeeViewModel(
            employeeToEdit.name,
            employeeToEdit.login,
            employeeToEdit.phone
        ))*/
        val webForm = formLens(request)
        if (webForm.errors.isEmpty()) {
            editEmployeeOperation.edit(
                Employee(
                    employeeToEdit.id,
                    nameLens(webForm),
                    loginLens(webForm),
                    phoneLens(webForm)
                )
            )
            return Response(Status.FOUND).header("location", "/employees/${employeeToEdit.id}")
        }
        return Response(Status.BAD_REQUEST).with(htmlView(request) of EditFormEmployeeViewModel(
            webForm = webForm
        )
        )
    }
}*/