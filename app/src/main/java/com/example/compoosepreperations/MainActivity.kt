package com.example.compoosepreperations

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import com.example.compoosepreperations.ui.screens.disposablesideeffects.DisposableSideEffectHomeScreen
import com.example.compoosepreperations.ui.screens.launcheffects.LaunchEffectsScreen
import com.example.compoosepreperations.ui.screens.producestateeffectscreen.ProduceEffectScreen
import com.example.compoosepreperations.ui.screens.producestateeffectscreen.UserProduceStateEffect
import com.example.compoosepreperations.ui.screens.rememberupdatedstateeffect.MainCompo
import com.example.compoosepreperations.ui.screens.rememberupdatedstateeffect.RememberUpdateStateEffect
import com.example.compoosepreperations.ui.screens.sideeffectsScreen.SideEffectsScreen
import com.example.compoosepreperations.ui.theme.CompoosePreperationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompoosePreperationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
//                    LoginScreen()
//                    ChangeFromOutSide()
//                    LaunchEffectsScreen()
//                    RememberUpdateStateEffect()
//                    MainCompo()

//                    DisposableSideEffectHomeScreen(startEvent = {
//                        Log.d("LifeCycleEvent","OnStart")
//                    }, stopEvent = {
//                        Log.d("LifeCycleEvent","OnStop")
//                    }, lifecycleOwner = LocalLifecycleOwner.current)

//                    SideEffectsScreen()
//                    ProduceEffectScreen()
                    UserProduceStateEffect()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CompoosePreperationsTheme {
//        Greeting("Android")
//    }
//}