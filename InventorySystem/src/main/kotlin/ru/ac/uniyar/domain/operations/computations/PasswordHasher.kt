package ru.ac.uniyar.domain.operations.computations

fun hashPassword(password: String, salt: String): String {
    val saltedPassword = password + salt
    return Checksum(saltedPassword.toByteArray()).toString()

   /* var generatePass = "123" + "EqYJUCsZqZI1_ql1xcLsOKJ9TAChMsq3edZrZjnlaliB7B3E83UoYfsZtRuxL7BVVrqVwJtzMYehiFI4013ubw"
    generatePass = Checksum(generatePass.toByteArray()).toString()
    var tmp=1
    return "asdas"*/
}