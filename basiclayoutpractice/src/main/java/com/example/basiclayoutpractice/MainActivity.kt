package com.example.basiclayoutpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basiclayoutpractice.ui.theme.CompoosePreperationsTheme

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
//                VerticalPagerWidget()
//                LaunchEffectWithPager()
//                HorizontalPagerSample()
//                HorizontalPagerTabRowSample()
//                FlowRowWidget()
//                flowGridView(50,10)
//                FlowRowItemsWidget()
//                CustomLayoutWidget()
//                ReverseFlowRowPreview()
//                CustomSquare()
//                WebViewWidget()
                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize(), color = Color.Red) {
        Column(

            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Happy Birthday  $name! ",
                modifier = modifier,
                fontSize = 100.sp,
                lineHeight = 116.sp,
                textAlign = TextAlign.Center

            )

            Text(

                text = "From Emma",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.End),
                fontSize = 50.sp,
                lineHeight = 60.sp,


                )


        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoosePreperationsTheme {
        Greeting("Sam")
    }
}