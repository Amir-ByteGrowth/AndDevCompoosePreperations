package com.example.navigationandfunctions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.navigationandfunctions.ui.components.RallyTabRow
import com.example.navigationandfunctions.ui.navigations.RallyNavHost
import com.example.navigationandfunctions.ui.screens.accounts.AccountsScreen
import com.example.navigationandfunctions.ui.screens.accounts.SingleAccountScreen
import com.example.navigationandfunctions.ui.screens.bills.BillsScreen
import com.example.navigationandfunctions.ui.screens.overview.OverviewScreen
import com.example.navigationandfunctions.ui.theme.CompoosePreperationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            CompoosePreperationsTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                    FunctionsList().showResult()
//                }
//            }
            RallyAppContent()
        }
    }
}


@Composable
fun RallyAppContent(modifier: Modifier = Modifier) {
    CompoosePreperationsTheme {

        var currentScreen: RallyDestination by remember {
            mutableStateOf(Overview)
        }

//        Scaffold(topBar = {
//            RallyTabRow(
//                allScreens = rallyTabsRowScreens,
//                onTabSelected = { screen -> currentScreen = screen },
//                currentScreen = currentScreen
//            )
//        }) { innerPadding ->
//            Box(modifier = modifier.padding(innerPadding)) {
//                currentScreen.screen()
//            }
//
//        }

        val navController = rememberNavController()
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination
        currentScreen =
            rallyTabsRowScreens.find { it.route == currentDestination?.route } ?: Overview

        Scaffold(topBar = {
            RallyTabRow(
                allScreens = rallyTabsRowScreens,
                onTabSelected = { newScreen ->
                    navController.navigateSingleTopTo(newScreen.route)
//                    currentScreen=newScreen
                },
                currentScreen = currentScreen
            )
        }) { innerPadding ->
            RallyNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
//            NavHost(
//                navController = navController,
//                startDestination = Overview.route,
//                modifier = Modifier.padding(innerPadding)
//            ) {
//                composable(route = Overview.route) {
//                    OverviewScreen(onClickSeeAllAccounts = {
//                        navController.navigateSingleTopTo(
//                            Accounts.route
//                        )
//                    }, onClickSeeAllBills = {
//                        navController.navigateSingleTopTo(Bills.route)
//                    }, onAccountClick = {
//                        navController.navigateToSingleAccount(it)
//                    }
//
//                    )
//                }
//                composable(route = Accounts.route) {
//                    AccountsScreen(onAccountClick = {navController.navigateToSingleAccount(it)} )
//                }
//                composable(route = Bills.route) {
//                    BillsScreen()
//                }
//                composable(route = SingleAccount.routeWithArgs,
//                    arguments = SingleAccount.arguments,
//                    deepLinks = SingleAccount.deepLinks
//                ) {
//                        navBackStackEntry ->
//                    // Retrieve the passed argument
//                    val accountType =
//                        navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
//                    SingleAccountScreen(accountType = accountType)
//                }
//            }

        }

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {

//        popUpTo(
//            route
//        ) {
//            saveState = true
//
//        }
        launchSingleTop = true
//        restoreState = true

        //to add only for single time in stack
//        popUpTo(
//            route
//        ) {
//            inclusive=true
//
//        }

    }

private fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoosePreperationsTheme {
        Greeting("Android")
    }
}