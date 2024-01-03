package com.example.basiclayoutpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basiclayoutpractice.ui.theme.CompoosePreperationsTheme
import com.example.basiclayoutpractice.widgets.CardWithLandPortraitBoCons
import com.example.basiclayoutpractice.widgets.ConstraintBoxWithCol
import com.example.basiclayoutpractice.widgets.CustomModifierUsingComposable
import com.example.basiclayoutpractice.widgets.CustomModifierWidget
import com.example.basiclayoutpractice.widgets.HorizontalPagerWidget
import com.example.basiclayoutpractice.widgets.ListOfFloatingActionButton
import com.example.basiclayoutpractice.widgets.UserRowWithThumbImg
import com.example.basiclayoutpractice.widgets.VerticalPagerWidget

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompoosePreperationsTheme {
                // A surface container using the 'background' color from the theme

//                    UserRowCard()
//                StackView()
//                UserRowWithThumbImg()
//                ConstraintBoxWithCol()
//                CardWithLandPortraitBoCons()
//                ListOfFloatingActionButton()
//                CustomModifierUsingComposable()
//                HorizontalPagerWidget()
                VerticalPagerWidget()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoosePreperationsTheme {
        Greeting("Android")
    }
}