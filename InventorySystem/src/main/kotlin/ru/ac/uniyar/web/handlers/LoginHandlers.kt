package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.core.cookie.Cookie
import org.http4k.core.cookie.SameSite
import org.http4k.core.cookie.cookie
import org.http4k.core.cookie.invalidateCookie
import org.http4k.lens.*
import ru.ac.uniyar.domain.operations.AuthenticateUserViaLoginQuery
import ru.ac.uniyar.domain.operations.AuthenticationError
import ru.ac.uniyar.web.filters.JwtTools
import ru.ac.uniyar.web.models.LoginFormVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class ShowLoginFormHandler(
    private val htmlView: ContextAwareViewRender
) : HttpHandler{
    override fun invoke(request: Request): Response {
        return Response(Status.OK).with(htmlView(request) of LoginFormVM())
    }
}

class AuthenticateUser(
    private val authenticateUserViaLoginQuery: AuthenticateUserViaLoginQuery,
    private val htmlView: ContextAwareViewRender,
    private val jwtTools: JwtTools,
): HttpHandler{
    companion object{
        private val loginFieldLens = FormField.nonEmptyString().required("login")
        private val passwordFieldLens = FormField.nonEmptyString().required("password")
        private val formLens = Body.webForm(Validator.Feedback, loginFieldLens, passwordFieldLens).toLens()
    }

    override fun invoke(request: Request): Response {
        val form = formLens(request)
        if (form.errors.isNotEmpty()) {
            return Response(Status.BAD_REQUEST).with(htmlView(request) of LoginFormVM(form))
        }
        val userId = try {
            authenticateUserViaLoginQuery(loginFieldLens(form), passwordFieldLens(form))
        } catch (_: AuthenticationError){
            val newErrors = form.errors + Invalid(
                passwordFieldLens.meta.copy(description = "login or password should match")
            )
            val newForm = form.copy(errors = newErrors)
            return Response(Status.BAD_REQUEST).with(htmlView(request) of LoginFormVM(newForm))
        }
        val token = jwtTools.create(userId) ?: return Response(Status.INTERNAL_SERVER_ERROR)
        val authCookie = Cookie("token", token, httpOnly = true, sameSite = SameSite.Strict)
        return Response(Status.FOUND)
            .header("Location", "/")
            .cookie(authCookie)


    }
}

class LogOutUser : HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(Status.FOUND)
            .header("Location", "/")
            .invalidateCookie("token")
    }
}