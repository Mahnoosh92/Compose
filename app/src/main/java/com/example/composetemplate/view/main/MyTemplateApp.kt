package com.example.composetemplate.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetemplate.view.dashboard.home.HomeScreen
import com.example.composetemplate.view.dashboard.profile.ProfileScreen
import com.example.composetemplate.view.dashboard.settings.SettingScreen
import com.example.composetemplate.view.destinations.*
import com.example.composetemplate.view.details.Details

data class DashBoardScreens(val icon: ImageVector, val name: String)

val allDashBoardScreens = listOf<DashBoardScreens>(
    DashBoardScreens(icon = Icons.Filled.Home, name = "home"),
    DashBoardScreens(icon = Icons.Filled.Settings, name = "setting"),
    DashBoardScreens(icon = Icons.Filled.AccountCircle, name = "profile")
)

@Composable
fun MyApp(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    var currentDestination =
        allDestinations.find { it.route == currentBackStack?.destination?.route }
    var currentScreen by remember {
        mutableStateOf(allDashBoardScreens[0])
    }
    var bottomNavVisibility by remember(currentDestination) {
        mutableStateOf(
            currentDestination?.route in listOf(
                Home.route, Setting.route, Profile.route
            )
        )
    }
    Scaffold(topBar = {}) { paddings ->
        Column() {
            MyNavHost(
                modifier = Modifier
                    .padding(paddings)
                    .weight(1f),
                navHostController = navController,
                myViewModel = viewModel()
            )
            MyBottomNavigation(
                modifier = Modifier.padding(paddings),
                navHostController = navController,
                bottomScreens = allDashBoardScreens,
                selected = currentScreen,
                visibility = bottomNavVisibility

            )
        }
    }
}

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier, navHostController: NavHostController, myViewModel: MyViewModel
) {
    var selectedTab by remember {
        mutableStateOf(myViewModel.tabsList[0])
    }
    NavHost(modifier = modifier, navController = navHostController, startDestination = Home.route) {
        composable(route = Home.route) {
            HomeScreen(
                tabs = myViewModel.tabsList,
                selectedTab = selectedTab,
                onTabClicked = {
                    myViewModel.updateTabs(it)
                    selectedTab = it
                },
                onAccountClicked = { navHostController.navigateToSingleAccount(it.name) },
                onBillClicked = { navHostController.navigateToSingleBill(it.name) }
            )
        }
        composable(route = Setting.route) {
            SettingScreen()
        }
        composable(route = Profile.route) {
            ProfileScreen()
        }
        composable(
            route = SingleAccount.routeWithArgs,
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        ) { navBackStackEntry ->
            val accountType = navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            Details()
        }
    }
}

@Composable
fun MyBottomNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    bottomScreens: List<DashBoardScreens>,
    selected: DashBoardScreens,
    visibility: Boolean
) {
    if (visibility) {
        BottomNavigation() {
            bottomScreens.forEach { item ->
                BottomNavigationItem(selected = item.name == selected.name, onClick = {
                    navHostController.navigateSingleTopTo(item.name)
                }, icon = { Icon(imageVector = item.icon, contentDescription = item.name) })
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
}

private fun NavHostController.navigateToSingleBill(billType: String) {
    this.navigateSingleTopTo("${SingleBill.route}/$billType")
}