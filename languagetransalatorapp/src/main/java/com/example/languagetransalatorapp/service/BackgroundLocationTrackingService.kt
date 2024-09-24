package com.example.languagetransalatorapp.service

import android.Manifest
import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.languagetransalatorapp.CHANNEL_ID
import com.example.languagetransalatorapp.R
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

// this service is noticeable by the user
// it has highest priority
// this service is not killed by android os
class BackgroundLocationTrackingService : Service() {


    private val locationRequest by lazy {
        com.google.android.gms.location.LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            1000
        ).setIntervalMillis(10000).build()
    }

    private val locationCallback by lazy {
        object : LocationCallback() {
            override fun onLocationAvailability(p0: LocationAvailability) {
                super.onLocationAvailability(p0)
            }

            override fun onLocationResult(locationResult: LocationResult) {
                val lat = locationResult.lastLocation?.latitude.toString()
                val long = locationResult.lastLocation?.longitude.toString()
                startServiceOfForeground(lat, long)
            }
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    // methods needed foreground service
    // needed to add forgroundservicetype = "location" in service tag in manifest
    // needed to add notification permission and location permission
    //    <uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
    //    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    //    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    //
    //     to get permission for foreground service and type of foreground service
    //    <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
    //    <uses-permission android:name="android.permission.FOREGROUND_SERVICE_LOCATION"/>
    //    to get location in background
    //    <uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION"/>


    override fun onCreate() {
        super.onCreate()
    }


    // if we are using 10 different intents they will have 10 different start ids, if our service fails it will check which id intent is stopped without completing it it will start it again


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        stopSelf(startId)
        locationUpdates()
        return START_STICKY
    }

    //start_sticky if your service crashed due to some reasons it will ask android framework to reschedule our task without providing us intent we use while creating this service
    // start_redeliver intent it will ask android framework to reschedule our task by providing us intent we use while creating this service
    // start_non_Sticky it will tell android framework not to reschedule our work

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun locationUpdates() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null)
    }


    fun startServiceOfForeground(lat: String, long: String) {

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Live Location")
            .setContentText("$lat  -  $long")
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startForeground(1, notification)
            }
        } else {
            startForeground(1, notification)
        }
    }

// this will remove the notification and convert foreground service to background service os will kill this
//    stopforegroundservice(removeNotification = true) or
//    stopForeground (STOP_FOREGROUND_REMOVE) // true
    // if it is false it will not remove notification but convert it into background service and os will kill this as well
//    stopforegroundservice(removeNotification = false) or
//    stopForeground (STOP_FOREGROUND_DETACH) // false

    // if we use stopSelf() it will eventually it self or stop it self

}