package com.example.basiclayoutpractice.widgets.constraintlayoutwidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun ConstraintBarriers() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val (text1, text2, text3, text4, text5) = createRefs()
        val barrierStart = createStartBarrier()
        val barrierEnd = createEndBarrier(text1, text2, text3, text4)
        val barrierTop = createTopBarrier()
        val barrierBottom = createBottomBarrier()

        Text(text = "Amir", modifier = Modifier.constrainAs(text1) {

        })
        Text(text = "is a", modifier = Modifier.constrainAs(text2) {
            top.linkTo(text1.bottom)

        })
        Text(text = "good boy here", modifier = Modifier.constrainAs(text3) {
            top.linkTo(text2.bottom)

        })
        Text(text = "Do you know him?", modifier = Modifier.constrainAs(text4) {
            top.linkTo(text3.bottom)

        })

        Text(text = "Text on right side of barrier", modifier = Modifier.constrainAs(text5) {
            start.linkTo(barrierEnd)
        })


    }
}

@Preview
@Composable
fun ConstraintBarriersPrev() {
    ConstraintBarriers()
}