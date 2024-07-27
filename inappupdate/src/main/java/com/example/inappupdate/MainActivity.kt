package com.example.inappupdate

import android.content.IntentSender
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextPainter
import androidx.compose.ui.tooling.preview.Preview
import com.example.inappupdate.ui.theme.CompoosePreperationsTheme
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

class MainActivity : ComponentActivity() {


    private lateinit var appUpdateManager: AppUpdateManager
    private lateinit var appUpdateLauncher: ActivityResultLauncher<IntentSenderRequest>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the app update manager
        appUpdateManager = AppUpdateManagerFactory.create(this)

        // Register the activity result launcher in onCreate
//        There are several values you might receive from the onActivityResult() callback:
//
//RESULT_OK: The user has accepted the update. For immediate updates, you might not receive this callback because the update should already be finished by the time control is given back to your app.
//RESULT_CANCELED: The user has denied or canceled the update.
//ActivityResult.RESULT_IN_APP_UPDATE_FAILED: Some other error prevented either the user from providing consent or the update from proceeding.
        appUpdateLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode != RESULT_OK) {
                Toast.makeText(this, "Update failed! Result code: ${result.resultCode}", Toast.LENGTH_SHORT).show()
            }
        }
        enableEdgeToEdge()
//        checkUpdate()
        // Check for app updates
        checkForAppUpdate()
        setContent {
            CompoosePreperationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    private fun checkUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(applicationContext)

// Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // This example applies an immediate update. To apply a flexible update
                // instead, pass in AppUpdateType.FLEXIBLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Request the update.
                Toast.makeText(applicationContext, "Update Available", Toast.LENGTH_SHORT).show()
            } else {

                startAppUpdate(appUpdateInfo)
                Toast.makeText(
                    applicationContext,
                    "Update Not Available With Status  " + appUpdateInfo.availableVersionCode()
                        .toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    fun checkHowManyDaysBeforeFlexibleUpdateWasGiven() {
        val appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        val updateInfo = appUpdateManager.appUpdateInfo
        val DAYS_FOR_FLEXIBLE_UPDATE = 6
        updateInfo.addOnSuccessListener { appUpdateInfo ->

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && (appUpdateInfo.clientVersionStalenessDays() ?: -1) >= DAYS_FOR_FLEXIBLE_UPDATE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
            }

        }

    }

    fun checkPriorityOfUpdate() {

        val appUpdateInfo = appUpdateManager.appUpdateInfo
        appUpdateInfo.addOnSuccessListener { updateInfo ->

            if (updateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && updateInfo.updatePriority() > 4
                && updateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
            }


        }
    }


    private fun startAppUpdate(appUpdateInfo: AppUpdateInfo){

        appUpdateManager.startUpdateFlowForResult(
            // Pass the intent that is returned by 'getAppUpdateInfo()'.
            appUpdateInfo,
            // an activity result launcher registered via registerForActivityResult
            appUpdateLauncher,
            // Or pass 'AppUpdateType.FLEXIBLE' to newBuilder() for
            // flexible updates.
            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build(),

        )
    }


    /////start update
    private fun checkForAppUpdate() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.
                startAppUpdate(appUpdateInfo, AppUpdateType.IMMEDIATE)
            }
        }
    }

    private fun startAppUpdate(appUpdateInfo: AppUpdateInfo, updateType: Int) {
        val updateOptions = AppUpdateOptions.newBuilder(updateType).build()

        try {
            appUpdateManager.startUpdateFlowForResult(
                appUpdateInfo,
                appUpdateLauncher,
                updateOptions
            )
        } catch (e: IntentSender.SendIntentException) {
            e.printStackTrace()
            Toast.makeText(this, "Error starting update flow: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                // If an in-app update is already in progress, resume the update.
                startAppUpdate(appUpdateInfo, AppUpdateType.IMMEDIATE)
            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                // After the update is downloaded, prompt the user to restart the app.
                popupSnackbarForCompleteUpdate()
            }
        }
    }

    private fun popupSnackbarForCompleteUpdate() {
        Toast.makeText(applicationContext,"An Update has been downloaded",Toast.LENGTH_SHORT).show()
//        Snackbar.make(
//            findViewById(R.id.main_layout),
//            "An update has just been downloaded.",
//            Snackbar.LENGTH_INDEFINITE
//        ).apply {
//            setAction("RESTART") { appUpdateManager.completeUpdate() }
//            show()
//        }
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoosePreperationsTheme {
        Greeting("Android")
    }
}