package com.example.basiclayoutpractice.widgets.constraintlayoutwidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun DeCoupleConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            deCoupleConstraints(margin = 16.dp) // Portrait constraints
        } else {
            deCoupleConstraints(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("button")) {
                Text(text = "Decouple cons btn")
            }

            Text(
                text = "Decouple cons Text",
                modifier = Modifier
                    .layoutId("text")
                    .background(color = Color.White)
            )

        }

    }
}


private fun deCoupleConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")
        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
            start.linkTo(button.start)
            end.linkTo(button.end)
        }
    }
}


@Preview
@Composable
fun DeCoupleConstraintLayoutPre() {
    DeCoupleConstraintLayout()
}