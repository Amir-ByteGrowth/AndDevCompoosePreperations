package com.example.errorhandlingincleanarchitecture.data


import com.example.errorhandlingincleanarchitecture.domain.AuthRepository
import com.example.errorhandlingincleanarchitecture.domain.DataError
import com.example.errorhandlingincleanarchitecture.domain.Result
import com.example.errorhandlingincleanarchitecture.domain.User
import com.example.errorhandlingincleanarchitecture.domain.UserDataValidator
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl : AuthRepository {

    override suspend fun register(password: String): Result<User, DataError.Network> {
        // api logic
        return try {
            val user = User("Dummy", "Dummy", "Dummy")
            return Result.Success(user)
        } catch (excRetrofit: HttpException) {
            when (excRetrofit.code()) {
                408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }
        // we can add more exception here
//        catch (e:IOException){
//
//        }

    }
}