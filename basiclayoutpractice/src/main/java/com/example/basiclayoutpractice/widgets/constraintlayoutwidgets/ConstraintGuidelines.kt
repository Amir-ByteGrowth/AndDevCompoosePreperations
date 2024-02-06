package com.example.basiclayoutpractice.widgets.constraintlayoutwidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun ConstraintGuidelines() {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        val guidelineStart = createGuidelineFromStart(0.25f)
        val guidelineEnd = createGuidelineFromEnd(0.1f)
        val guidelineTop = createGuidelineFromTop(0.15f)
        val guidelineBottom = createGuidelineFromBottom(0.25f)
        val (button, text) = createRefs()
        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button) {
            top.linkTo(guidelineTop)
            start.linkTo(guidelineStart)
        }) {
            Text(text = "Guideline Button")
        }

        Text(text = "GuideLine Text", modifier = Modifier.constrainAs(text){
            bottom.linkTo(guidelineBottom)
            start.linkTo(guidelineStart)
        })

    }
}

@Preview
@Composable
fun ConstraintGuidelinesPrev() {
    ConstraintGuidelines()
}