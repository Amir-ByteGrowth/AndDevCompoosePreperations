package com.example.navigationandfunctions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.navigationandfunctions.ui.screens.accounts.AccountsScreen
import com.example.navigationandfunctions.ui.screens.bills.BillsScreen
import com.example.navigationandfunctions.ui.screens.overview.OverviewScreen

interface RallyDestination {
    val icon: ImageVector
    val route: String
//    val screen: @Composable () -> Unit
}

object Overview : RallyDestination {
    override val icon: ImageVector
        get() = Icons.Filled.PieChart
    override val route: String
        get() = "overview"
//    override val screen: @Composable () -> Unit
//        get() = { OverviewScreen() }

}

object Accounts : RallyDestination {
    override val icon: ImageVector
        get() = Icons.Filled.AttachMoney
    override val route: String
        get() = "accounts"
//    override val screen: @Composable () -> Unit
//        get() = { AccountsScreen() }

}

object Bills : RallyDestination {
    override val icon: ImageVector
        get() = Icons.Filled.MoneyOff
    override val route: String
        get() = "bills"
//    override val screen: @Composable () -> Unit
//        get() = { BillsScreen() }

}

object SingleAccount : RallyDestination {
    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
    // part of the RallyTabRow selection
    override val icon = Icons.Filled.Money
    override val route = "single_account"

    const val accountTypeArg = "account_type"

    val routeWithArgs = "${route}/{${accountTypeArg}}"
    val deepLinks = listOf(
        navDeepLink { uriPattern = "rally://$route/{$accountTypeArg}"}
    )

    val arguments = listOf(
        navArgument(accountTypeArg) { type = NavType.StringType }
    )
}

val rallyTabsRowScreens = listOf(Overview, Accounts, Bills)