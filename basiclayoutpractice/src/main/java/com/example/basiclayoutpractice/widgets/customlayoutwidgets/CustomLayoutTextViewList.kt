package com.example.basiclayoutpractice.widgets.customlayoutwidgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ReverseFlowRowPreview() {
    ReverseFlowRow(mainAxisSpacing = 16.dp, crossAxisSpacing = 16.dp) {
        Text("First", fontSize = 20.sp, style = TextStyle(background = Color.Red))
        Text("Second", fontSize = 20.sp, style = TextStyle(background = Color.LightGray))
        Text("Third", fontSize = 20.sp, style = TextStyle(background = Color.Blue))
        Text("Fourth", fontSize = 20.sp, style = TextStyle(background = Color.Green))
        Text("Fifth", fontSize = 20.sp, style = TextStyle(background = Color.Gray))
        Text("Sixth", fontSize = 20.sp, style = TextStyle(background = Color.Yellow))
        Text("Seventh", fontSize = 20.sp, style = TextStyle(background = Color.Cyan))
        Text("Eight", fontSize = 20.sp, style = TextStyle(background = Color.Magenta))
        Text("Ninth", fontSize = 20.sp, style = TextStyle(background = Color.DarkGray))
    }
}

@Composable
fun ReverseFlowRow(
    mainAxisSpacing: Dp,
    crossAxisSpacing: Dp,
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
        var xPosition = constraints.maxWidth

        placeables.forEach { placeable ->
            if (placeable.width < (xPosition + mainAxisSpacing.roundToPx())) {
                xPosition -= (placeable.width + mainAxisSpacing.roundToPx())
            } else {
                yPosition += (placeable.height + crossAxisSpacing.roundToPx())
                xPosition = constraints.maxWidth - placeable.width - mainAxisSpacing.roundToPx()
            }
            placeable.placeRelative(xPosition, yPosition)
        }
    }
}