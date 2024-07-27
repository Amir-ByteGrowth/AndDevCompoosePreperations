package com.example.inappupdate

import android.content.IntentSender
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

class FlexibleUpdateActivity : AppCompatActivity() {

    lateinit var appUpdateManager: AppUpdateManager
    lateinit var launcher: ActivityResultLauncher<IntentSenderRequest>
    lateinit var progressBar: ProgressBar

    // listener for update progress
    val listener = InstallStateUpdatedListener { state ->
        if (state.installStatus() == InstallStatus.DOWNLOADING) {
            val bytesDownloaded = state.bytesDownloaded()
            val totalBytesToDownload = state.totalBytesToDownload()
            // Update the progress bar here
            val progress = (100 * bytesDownloaded / totalBytesToDownload).toInt()
            progressBar.progress = progress
        } else if (state.installStatus() == InstallStatus.DOWNLOADED) {
            popupSnackbarForCompleteUpdate()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_flexible_update)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progress)

        // Initialize the app update manager
        appUpdateManager = AppUpdateManagerFactory.create(this)

        // Register the activity result launcher in onCreate
        launcher =
            registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                if (result.resultCode != RESULT_OK) {
                    Toast.makeText(
                        this,
                        "Update failed! Result code: ${result.resultCode}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        // Register the listener
        appUpdateManager.registerListener(listener)

        // Check for app updates
        checkForAppUpdate()


    }


    private fun checkForAppUpdate() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                // Request the update.
                startAppUpdate(appUpdateInfo, AppUpdateType.FLEXIBLE)
            }
        }
    }

    private fun startAppUpdate(appUpdateInfo: AppUpdateInfo, updateType: Int) {
        val updateOptions = AppUpdateOptions.newBuilder(updateType).build()

        try {
            appUpdateManager.startUpdateFlowForResult(
                appUpdateInfo,
                launcher,
                updateOptions
            )
        } catch (e: IntentSender.SendIntentException) {
            e.printStackTrace()
            Toast.makeText(this, "Error starting update flow: ${e.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()

        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                // If an in-app update is already in progress, resume the update.
                startAppUpdate(appUpdateInfo, AppUpdateType.FLEXIBLE)
            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                // After the update is downloaded, prompt the user to restart the app.
                popupSnackbarForCompleteUpdate()
            }
        }
    }

    private fun popupSnackbarForCompleteUpdate() {
        Snackbar.make(
            findViewById(R.id.main),
            "An update has just been downloaded.",
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("RESTART") { appUpdateManager.completeUpdate() }
            show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appUpdateManager.unregisterListener(listener)
    }


}