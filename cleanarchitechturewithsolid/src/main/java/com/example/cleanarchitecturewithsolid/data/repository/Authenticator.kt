package com.example.cleanarchitecturewithsolid.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider

interface Authenticator {
 suspend fun login(email:String,password:String)
}


class FirebaseAuthenticator : Authenticator{

    override suspend fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
    }
}

class CustomApiAuthenticator : Authenticator{
// we will use retro fit call here
    override suspend fun login(email: String, password: String) {

    }
}