package com.example.statehoistingpreperations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.statehoistingpreperations.differentwaystostorestate.CityObjectSaverUsingListSaverScreen

import com.example.statehoistingpreperations.differentwaystostorestate.UsingMapSaverScreen
import com.example.statehoistingpreperations.handlesysteminitiatedprocessdeath.CounterScreen
import com.example.statehoistingpreperations.handlesysteminitiatedprocessdeath.HandleStateScreen
import com.example.statehoistingpreperations.handlesysteminitiatedprocessdeath.UserInput

import com.example.statehoistingpreperations.localcompositionexm.LocalCompositionForRemember
import com.example.statehoistingpreperations.localcompositionexm.LocalCompositionScreen
import com.example.statehoistingpreperations.localcompositionexm.MyApp
import com.example.statehoistingpreperations.localcompositionexm.MyAppStatic
import com.example.statehoistingpreperations.localcompositionexm.StaticLocalComposition
import com.example.statehoistingpreperations.plainstateholders.MyTextField
import com.example.statehoistingpreperations.plainstateholders.PlainStateHolderScreen
import com.example.statehoistingpreperations.rememberwithkey.RememberWithKey
import com.example.statehoistingpreperations.ui.theme.CompoosePreperationsTheme

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
//                    ParentComponent()
//                    ParentComponentLC()
//                    ParentComposable()
//                    ParentComposableWODRS()
//                    DerivedStatesOfToLimitRecomp()
//                    ProductSearchScreen()
//                    EdittextDerivedStateOf()

//                    CustomSaverScreen()
//                    MyListComposable()
//                    ForListOfUsers()
//                    MyCustomObserveAbleScreen()
//                    UserListComposable()
//                    CityScreen()
//                    CityObjectSaverUsingListSaverScreen()
//                    RememberWithKey()
//                    PlainStateHolderScreen()
//                    MyTextField("Enter Name Here")
//                    CounterScreen()
//                    HandleStateScreen()
//                    UserInput()
//                    LocalCompositionScreen()
//                    LocalCompositionForRemember()
//                    StaticLocalComposition()
                    MyAppStatic()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,stateHolder: TextStateHolder = remember { TextStateHolder() }) {

    val uiState by stateHolder.uiState.collectAsState()


    Text(
        text = "Hello ${uiState.text}!",
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