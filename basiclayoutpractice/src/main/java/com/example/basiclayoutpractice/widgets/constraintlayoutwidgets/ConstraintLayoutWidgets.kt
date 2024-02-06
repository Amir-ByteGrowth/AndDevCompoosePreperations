package com.example.basiclayoutpractice.widgets.constraintlayoutwidgets

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun ConstraintLayoutWidgets() {
    ConstraintLayout {
        val (button, text) = createRefs()

        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button) {
            top.linkTo(parent.top, margin = 16.dp)
        }) {
            Text(text = "Cons Button")
        }

        Text(text = "Cons Text", modifier = Modifier
            .constrainAs(text) {
                top.linkTo(button.bottom, margin = 10.dp)
                start.linkTo(button.start)
                end.linkTo(button.end)
            }
            .background(color = Color.White))
    }
}

@Preview
@Composable
fun ConstraintWidgetPreview() {
    ConstraintLayoutWidgets()
}