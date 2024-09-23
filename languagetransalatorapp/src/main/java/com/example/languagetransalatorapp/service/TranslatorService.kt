package com.example.languagetransalatorapp.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.compose.ui.text.intl.Locale
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions


class TranslatorService : Service() {


    //
    val binder = TranslatorBinder()

    inner class TranslatorBinder : Binder() {
        // this method will be used to expose our service to our class , i binnder class is used to expose our service to our client
        fun getService() = this@TranslatorService

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder {
       return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return true
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }






    fun translate(text: String, onSuccess: (String) -> Unit, onFailure: () -> Unit) {
        Log.d("TextFound","InMethod")
        val options = TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.URDU).build()
        val client = Translation.getClient(options)
        client.downloadModelIfNeeded().addOnSuccessListener {
            client.translate(text).addOnSuccessListener {
                Log.d("TextFound",it)
                onSuccess.invoke(it)
            }.addOnFailureListener {
                Log.d("TextFound","Failed")
                onFailure.invoke()
            }
        }
    }

}