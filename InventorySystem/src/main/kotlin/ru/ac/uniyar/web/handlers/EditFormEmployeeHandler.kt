package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.lens.Path
import org.http4k.lens.uuid
import ru.ac.uniyar.domain.operations.FetchEmployeeOperation
import ru.ac.uniyar.web.models.EditFormEmployeeViewModel
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class EditFormEmployeeHandler (
    private val fetchEmployeeOperation: FetchEmployeeOperation,
    private val htmlView: ContextAwareViewRender
) : HttpHandler {
    companion object {
        private val idLens = Path.uuid().of("id")
    }

    override fun invoke(request: Request): Response {
        val employeeToEdit = fetchEmployeeOperation.fetch(idLens.invoke(request)) ?: return Response(Status.BAD_REQUEST)
        return Response(Status.OK).with(htmlView(request) of EditFormEmployeeViewModel(
            employeeToEdit.name,
            employeeToEdit.login,
            employeeToEdit.phone
        )
        )
    }
}