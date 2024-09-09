package com.example.errorhandlingincleanarchitecture.domain

class UserDataValidator {
    fun validatePassword(password: String): Result<Unit, PasswordError> {
        if (password.length < 9)
            return Result.Error(error = PasswordError.TOO_SHORT)

        val hasUpperCaseChar = password.any { it.isUpperCase() }
        if (!hasUpperCaseChar) {
            return Result.Error(error = PasswordError.NO_UPPERCASE)
        }

        val hasDigit = password.any { it.isDigit() }
        if (!hasDigit) {
            return Result.Error(error = PasswordError.NO_DIGIT)
        }
        // we don't care about
        return Result.Success(Unit)
    }

    enum class PasswordError : Error {
        TOO_SHORT,
        NO_UPPERCASE,
        NO_DIGIT
    }
}