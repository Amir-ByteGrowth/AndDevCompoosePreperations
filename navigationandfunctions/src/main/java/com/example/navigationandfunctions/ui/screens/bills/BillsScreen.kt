package com.example.navigationandfunctions.ui.screens.bills

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import com.example.navigationandfunctions.R
import com.example.navigationandfunctions.data.Bill
import com.example.navigationandfunctions.data.UserData
import com.example.navigationandfunctions.ui.components.BillRow
import com.example.navigationandfunctions.ui.components.StatementBody

@Composable
fun BillsScreen( bills: List<Bill> = remember { UserData.bills }) {
    StatementBody(
        modifier = Modifier.clearAndSetSemantics { contentDescription = "Bills" },
        items = bills,
        amounts = { bill -> bill.amount },
        colors = { bill -> bill.color },
        amountsTotal = bills.map { bill -> bill.amount }.sum(),
        circleLabel = stringResource(R.string.due),
        rows = { bill ->
            BillRow(bill.name, bill.due, bill.amount, bill.color)
        }
    )
}