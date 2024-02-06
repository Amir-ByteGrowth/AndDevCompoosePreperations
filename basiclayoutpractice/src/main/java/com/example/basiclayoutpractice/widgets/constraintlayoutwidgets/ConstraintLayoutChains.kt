package com.example.basiclayoutpractice.widgets.constraintlayoutwidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun ConstraintLayoutChains() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val text1 = createRef()
        val text2 = createRef()
        val text3 = createRef()

//        val horizontalChain =
//            createHorizontalChain(text1, text2, text3, chainStyle = ChainStyle.SpreadInside)
        val horizontalChain =
            createVerticalChain(text1, text2, text3, chainStyle = ChainStyle.SpreadInside)
        Text(text = "Abc", modifier = Modifier.constrainAs(text1){})
        Text(text = "Def", modifier = Modifier.constrainAs(text2){})
        Text(text = "Ghi", modifier = Modifier.constrainAs(text3){})

    }
}

@Preview
@Composable
fun ConstraintLayoutChainsPrev() {
    ConstraintLayoutChains()
}