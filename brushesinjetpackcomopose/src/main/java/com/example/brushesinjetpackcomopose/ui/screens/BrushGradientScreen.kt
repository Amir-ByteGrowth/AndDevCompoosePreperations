package com.example.brushesinjetpackcomopose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BrushGradientScreen(modifier: Modifier = Modifier) {

//    val horizontalColorSpots = arrayOf(0.0f to Color.Yellow, 0.2f to Color.Red, 1f to Color.Blue)


    val tileSize  = with(LocalDensity.current){
        50.dp.toPx()
    }
    val brushWithTile = Brush.horizontalGradient(colors = listOf(Color.Yellow,Color.Red, Color.Blue), endX = tileSize, tileMode = TileMode.Mirror)

    val horizontalColorSpots = arrayOf(0.0f to Color.Yellow, 0.5f to Color.Red, 1f to Color.Blue)
    val brushColorSpot = Brush.horizontalGradient(colorStops = horizontalColorSpots)
    val brush = Brush.horizontalGradient(colors = listOf(Color.Red, Color.Blue))
    val linearbrush = Brush.linearGradient(colors = listOf(Color.Red, Color.Blue))
    val verticalbrush = Brush.verticalGradient(colors = listOf(Color.Red, Color.Blue))
    val sweepbrush = Brush.sweepGradient(colors = listOf(Color.Red, Color.Blue))
    val radialbrush = Brush.radialGradient(colors = listOf(Color.Red, Color.Blue))

    LazyColumn(
        state = rememberLazyListState(),
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        //color Tiles
        item {
            Text(text = "Color Tiles Horizontal Gradient 1")
        }
        item {
            Box(modifier = modifier.requiredSize(200.dp).background(brushWithTile))
        }

        //color Spot
        item {
            Text(text = "Color Spot Horizontal Gradient 1")
        }
        item {
            androidx.compose.foundation.Canvas(modifier = modifier.size(200.dp)) {
                drawRect(brushColorSpot)
            }
        }

        
        item {
            Text(text = "Horizontal Gradient 1")
        }
        item {
            androidx.compose.foundation.Canvas(modifier = modifier.size(200.dp)) {
                drawRect(brush)
            }
        }

        //
        item {
            Text(text = "Linear Gradient 1")
        }
        item {
            androidx.compose.foundation.Canvas(modifier = modifier.size(200.dp)) {
                drawRect(linearbrush)
            }
        }

        //
        item {
            Text(text = "Vertical Gradient 1")
        }
        item {
            androidx.compose.foundation.Canvas(modifier = modifier.size(200.dp)) {
                drawRect(verticalbrush)
            }
        }
        //
        item {
            Text(text = "Radial Gradient 1")
        }
        item {
            androidx.compose.foundation.Canvas(modifier = modifier.size(200.dp)) {
                drawRect(radialbrush)
            }
        }
        //
        item {
            Text(text = "Sweep Gradient 1")
        }
        item {
            androidx.compose.foundation.Canvas(modifier = modifier.size(200.dp)) {
                drawRect(sweepbrush)
            }
        }

    }
}

@Preview
@Composable
private fun PreBrushScreen() {
    BrushGradientScreen()
}