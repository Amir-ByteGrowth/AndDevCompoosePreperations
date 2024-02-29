package com.example.owlpractice.screens.comonwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.owlpractice.R
import com.example.owlpractice.ui.theme.urbanist_bold
import com.example.owlpractice.ui.theme.urbanist_semi_bold

@Composable
fun CurrencyCodeTextView(
    code: String = "AED",
    fontFamily: FontFamily = urbanist_semi_bold,
    fontSize: TextUnit = 11.sp
) {
    Text(
        text = code,
        fontSize = fontSize,
        fontFamily = fontFamily,
        style = TextStyle(color = Color.White)
    )
}


@Composable
fun BalanceSectionLabelTextView(
    modifier: Modifier = Modifier,
    label: String = "Available Balance",
    fontFamily: FontFamily = urbanist_semi_bold,
    fontSize: TextUnit = 11.sp,
    showArrow: Boolean = true,
    iconSize: DpSize = DpSize(width = 10.dp, height = 12.dp),
    amount: String = "201,754.03",
    amountFontSize: TextUnit = 22.sp,
    currencyCodeSize: TextUnit = 11.sp
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = label,
                fontSize = fontSize,
                fontFamily = fontFamily,
                style = TextStyle(color = Color.White.copy(alpha = 0.7f))
            )
            if (showArrow)
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_open_page),
                    contentDescription = "openArrow",
                    modifier = Modifier.padding(top = 3.dp)
                )

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = amount,
                fontFamily = urbanist_bold,
                fontSize = amountFontSize,
                style = TextStyle(color = Color.White)
            )
            CurrencyCodeTextView(fontSize = currencyCodeSize)
        }
    }


}


//@Preview(showBackground = true, backgroundColor = 0x000000)
//@Composable
//fun PreviewBalanceSectionLabelText() {
//    BalanceSectionLabelTextView()
//}


@Composable
fun VaultspayServicesItem(modifier: Modifier = Modifier,icon:Int=R.drawable.send_money_no_bg ,label: String = "Send\nMoney") {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {

        Box(
            modifier = modifier
                .size(50.dp)
                .clip(shape = CircleShape)
                .background(
                    color = colorResource(
                        id = R.color.colorSecondarySkyBlue
                    ).copy(alpha = 0.2f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = "Icon"
            )
        }

        Text(
            text = label,
            fontFamily = urbanist_bold,
            fontSize = 10.sp,
            color = colorResource(id = R.color.white)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrevVaultspayServicesItem() {
    VaultspayServicesItem()
}




