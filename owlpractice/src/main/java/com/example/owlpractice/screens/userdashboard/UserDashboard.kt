package com.example.owlpractice.screens.userdashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.owlpractice.R
import com.example.owlpractice.screens.comonwidget.BalanceSectionLabelTextView
import com.example.owlpractice.screens.comonwidget.VaultspayServicesItem
import com.example.owlpractice.ui.theme.urbanist_bold
import com.example.owlpractice.ui.theme.urbanist_semi_bold

@Composable
fun UserDashboard(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Green)
    ) {
        Image(
            painterResource(id = R.drawable.dashboard_background),
            contentDescription = "Background", contentScale = ContentScale.Fit,
            modifier = modifier.fillMaxSize()
        )

        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = modifier.height(intrinsicSize = IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.vp_logo_with_text),
                        contentDescription = "vplogo",
                        modifier = modifier
                            .wrapContentSize(),

                        )

                    Box(
                        modifier = modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .padding(vertical = 2.dp)
                            .alpha(0.5f)
                            .background(color = Color.White)


                    )

                    Text(
                        text = "Your Wallets",
                        fontFamily = urbanist_bold,
                        fontSize = 10.sp,
                        style = TextStyle(color = Color.White)
                    )
                }


                Box(
                    modifier = modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            color = colorResource(R.color.colorSecondarySkyBlue).copy(
                                alpha = 0.3f
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.notification_no_bg),
                        contentDescription = "notification", alignment = Alignment.Center,
                    )
                }
            }


            ConstraintLayout {
                val (balanceSectionRef, bottomBalanceSectionRef) = createRefs()

                Column(modifier = modifier

                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        color = colorResource(
                            id = R.color.colorSecondarySkyBlue
                        ).copy(alpha = 0.3f)
                    )
                    .constrainAs(balanceSectionRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }

                ) {
                    Row(
                        modifier = modifier
                            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BalanceSectionLabelTextView()

                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.aed_water_mark_dashboard),
                            contentDescription = "aedWater", modifier = modifier.size(50.dp)
                        )
                    }

                    Row(
                        modifier = modifier
                            .padding(start = 16.dp, top = 5.dp)
                            .fillMaxWidth()
                            .height(intrinsicSize = IntrinsicSize.Max),
                        horizontalArrangement = Arrangement.spacedBy(25.dp)
                    ) {
                        BalanceSectionLabelTextView(
                            label = "Balance On Hold",
                            fontSize = 10.sp,
                            amount = "0.00",
                            amountFontSize = 15.sp,
                            currencyCodeSize = 10.sp,
                            iconSize = DpSize(width = 8.dp, height = 20.dp)
                        )


                        Box(
                            modifier = modifier
                                .fillMaxHeight()
                                .width(1.dp)
                                .padding(vertical = 8.dp)
                                .alpha(0.3f)
                                .background(color = Color.White)


                        )

                        BalanceSectionLabelTextView(
                            label = "Total Balance",
                            fontSize = 10.sp,
                            amount = "201,754.03",
                            amountFontSize = 15.sp,
                            currencyCodeSize = 10.sp,
                            iconSize = DpSize(width = 8.dp, height = 20.dp),
                            showArrow = false
                        )

                    }

                    Text(
                        modifier = modifier.padding(start = 16.dp, top = 16.dp),
                        text = "Account Number: AED9277399329",
                        fontFamily = urbanist_semi_bold,
                        fontSize = 9.sp, style = TextStyle(color = Color.White.copy(alpha = 0.5f))
                    )

                    Row(modifier = modifier.wrapContentSize()) {
                        ConstraintLayout(
                            modifier = modifier
                                .padding(start = 16.dp, top = 10.dp, bottom = 16.dp)
                                .background(
                                    color = colorResource(
                                        id = R.color.colorSecondarySkyBlue
                                    ).copy(alpha = 0.2f), shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 5.dp)

                        ) {
                            val (iconRef, walledRef, codeRef, arrowIconRef) = createRefs()
                            Image(
                                painter = painterResource(id = R.drawable.aed),
                                contentDescription = "icon", modifier = modifier
                                    .constrainAs(iconRef) {
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                    }
                                    .size(25.dp)

                            )

                            Text(
                                text = "Wallet",
                                fontFamily = urbanist_semi_bold,
                                fontSize = 9.sp,
                                color = Color.White.copy(0.7f),
                                modifier = modifier.constrainAs(walledRef) {
                                    top.linkTo(iconRef.top)
                                    start.linkTo(iconRef.end, 5.dp)
                                }
                            )

                            Text(
                                text = "AED",
                                fontFamily = urbanist_bold,
                                fontSize = 10.sp,
                                color = Color.White.copy(0.7f),
                                modifier = modifier.constrainAs(codeRef) {
                                    top.linkTo(walledRef.bottom)
                                    start.linkTo(walledRef.start)
                                }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.expand_open_sheet_icon),
                                contentDescription = "expandedOpenSheet",
                                modifier = modifier
                                    .constrainAs(arrowIconRef) {
                                        start.linkTo(walledRef.end, 12.dp)
                                        top.linkTo(walledRef.top)
                                        bottom.linkTo(codeRef.bottom)
                                    }
                                    .size(8.dp),
                                colorFilter = ColorFilter.tint(Color.White)
                            )

                        }


                        ConstraintLayout(
                            modifier = modifier
                                .padding(start = 8.dp, top = 10.dp, end = 16.dp)
                                .background(
                                    color = colorResource(
                                        id = R.color.colorSecondarySkyBlue
                                    ).copy(alpha = 0.2f), shape = RoundedCornerShape(20.dp)
                                )
                                .padding(vertical = 5.dp)
                                .weight(1f)

                        ) {
                            val (depositRef, lineRef, withDrawRef) = createRefs()
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier.constrainAs(depositRef) {
                                    start.linkTo(parent.start)
                                    end.linkTo(lineRef.start)
                                }) {
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.deposit_icon),
                                    contentDescription = "DepositIcon"
                                )
                                Text(
                                    text = "Deposit",
                                    fontFamily = urbanist_bold,
                                    fontSize = 10.sp,
                                    color = colorResource(id = R.color.white)
                                )
                            }

                            Box(
                                modifier = modifier
                                    .width(1.dp)

                                    .background(color = Color.White.copy(alpha = 0.3f))
                                    .constrainAs(lineRef) {
                                        top.linkTo(parent.top, 4.dp)
                                        bottom.linkTo(parent.bottom, 4.dp)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        height = Dimension.fillToConstraints
                                    }

                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier.constrainAs(withDrawRef) {
                                    start.linkTo(lineRef.start)
                                    end.linkTo(parent.end)
                                }) {
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.withdraw_icon),
                                    contentDescription = "DepositIcon"
                                )
                                Text(
                                    text = "Withdraw",
                                    fontFamily = urbanist_bold,
                                    fontSize = 10.sp,
                                    color = colorResource(id = R.color.white)
                                )
                            }

                        }
                    }


                }

                Box(modifier = modifier
                    .constrainAs(bottomBalanceSectionRef) {
                        start.linkTo(balanceSectionRef.start, 42.dp)
                        end.linkTo(balanceSectionRef.end, 42.dp)
                        top.linkTo(balanceSectionRef.bottom, 2.dp)
                        width = Dimension.fillToConstraints
                    }
                    .height(10.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp
                        )
                    )
                    .background(
                        color = colorResource(id = R.color.colorSecondarySkyBlue).copy(
                            0.3f
                        )
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(top = 50.dp)
            ) {
                Spacer(
                    modifier = modifier
                        .height(1.dp)
                        .padding(end = 5.dp)
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white).copy(alpha = 0.25f))
                )
                Text(
                    text = "VaultsPay Services",
                    fontFamily = urbanist_bold,
                    fontSize = 12.sp,
                    color = colorResource(
                        id = R.color.white
                    )
                )
                Spacer(
                    modifier = modifier
                        .height(1.dp)
                        .padding(start = 5.dp)
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white).copy(alpha = 0.25f))
                )
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                VaultspayServicesItem()
                VaultspayServicesItem(icon = R.drawable.qr_p_ay_no_bg, label = "QR Pay")
                VaultspayServicesItem(icon = R.drawable.exchange_no_bg, label = "Exchange")
                VaultspayServicesItem(
                    icon = R.drawable.request_money_no_bg,
                    label = "Request\nMoney"
                )
            }
        }


    }
}

@Preview
@Composable
fun PreviewDashboard() {
    UserDashboard()
}