package com.example.cleanarchitecturewithsolid.domain.repository

import com.google.firebase.auth.FirebaseAuth

interface MainRepository {

    suspend fun login(email:String,password:String)
}