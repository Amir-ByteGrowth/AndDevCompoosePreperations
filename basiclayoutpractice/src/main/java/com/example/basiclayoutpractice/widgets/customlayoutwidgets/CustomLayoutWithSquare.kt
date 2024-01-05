package com.example.basiclayoutpractice.widgets.customlayoutwidgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp


@Composable
fun CustomSquare() {

       CustomLayoutWithSquare {
           Box(Modifier.size(height = 70.dp, width = 70.dp).background(color = Color.Red)) {

           }
           Box(Modifier.size(70.dp).background(color = Color.Green)) {

           }
           Box(Modifier.size(70.dp).background(color = Color.Blue)) {

           }
       }

}


@Composable
fun CustomLayoutWithSquare(

    content: @Composable () -> Unit
) = Layout(content) { measurables, constraints ->
    // 1. The measuring phase.
    val placeables = measurables.map { measurable ->
        measurable.measure(constraints)
    }

    // 2. The sizing phase.
    layout(constraints.maxWidth, constraints.maxHeight) {
        // 3. The placement phase.
        var yPosition = 0
        var xPosition = 0

        placeables.forEach { placeable ->
//            if (placeable.width < (xPosition + mainAxisSpacing.roundToPx())) {
//                xPosition -= (placeable.width + mainAxisSpacing.roundToPx())
//            } else {
//                yPosition += (placeable.height + crossAxisSpacing.roundToPx())
//                xPosition = constraints.maxWidth - placeable.width - mainAxisSpacing.roundToPx()
//            }


            if (xPosition == 0 && yPosition == 0) {
                placeable.placeRelative(xPosition, yPosition)
                Log.d("Positions" , "XPos  $xPosition   YPos    $yPosition")
                xPosition += ((placeable.width * 70) / 100)
                yPosition += ((placeable.height * 70) / 100)
            } else {
                
                Log.d("Positions" , "XPos  $xPosition   YPos    $yPosition")
                placeable.placeRelative(xPosition, yPosition)
                xPosition += ((placeable.width * 70) / 100)
                yPosition += ((placeable.height * 70) / 100)
            }

        }
    }
}