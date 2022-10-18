package ru.ac.uniyar.domain.storage
//пятое
class Settings {
    companion object{
    private val salt = "EqYJUCsZqZI1_ql1xcLsOKJ9TAChMsq3edZrZjnlaliB7B3E83UoYfsZtRuxL7BVVrqVwJtzMYehiFI4013ubw"
    }

    fun getSalt(): String{
        return salt
    }
}