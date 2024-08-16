package com.example.brushesinjetpackcomopose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.brushesinjetpackcomopose.ui.screens.BrushWithBackgroundImage
import com.example.brushesinjetpackcomopose.ui.theme.CompoosePreperationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompoosePreperationsTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .statusBarsPadding()
                ) { innerPadding ->
//                    BrushGradientScreen()
                    BrushWithBackgroundImage()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoosePreperationsTheme {
        Greeting("Android")
    }
}


fun main1() {
    val testArray: Array<Array<Int>> = arrayOf(
        arrayOf(-9, -9, -9, 1, 1, 1),
        arrayOf(0, -9, 0, 4, 3, 2),
        arrayOf(-9, -9, -9, 1, 2, 3),
        arrayOf(0,0,8,6,6,0),
        arrayOf(0,0,0,-2,0,0),
        arrayOf(0,0,1,2,4,0)
    )
var largestNumber :Int?  =null

    for(i in 0 until testArray.size-2){
        for(j in 0 until testArray[i].size-2){
            var sum =0
            var k = 0
            while (k <3){
                when (k) {
                    0 -> {
                        sum  += testArray[i][j+0]+testArray[i][j+1]+testArray[i][j+2]
//                        print("a"+sum+",")
                    }
                    1 -> {
//                    println(" ${i+1},${2+j},  "+testArray[(1+i)][(1+j)])
                        sum = sum + testArray[1+i][1+j]
//                        print("b"+sum+",")
                    }
                    2 -> {
                        sum += testArray[i+2][j+0]+testArray[i+2][j+1]+testArray[i+2][j+2]
//                        print("c"+sum+",")
                    }
                }
                k++
            }
            if (largestNumber == null){
               largestNumber = sum
            }
            else if (sum>largestNumber){
                largestNumber = sum
            }
            print("    "+sum.toString()+"  ")
        }
        println()
    }
println("Largest number is  $largestNumber")
}

fun main(){
    print("Enter name")
    val name = readln()
    println("Hello, World.\n$name")
}