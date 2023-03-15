package com.example.composetemplate.view.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface MyDestination {
    val icon: ImageVector
    val route: String
}

object Home : MyDestination {
    override val icon = Icons.Filled.Home
    override val route = "home"
}

object Setting : MyDestination {
    override val icon = Icons.Filled.Settings
    override val route = "setting"
}

object Profile : MyDestination {
    override val icon = Icons.Filled.Person
    override val route = "profile"
}
object SingleAccount : MyDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "single_account"
    const val accountTypeArg = "account_type"
    val routeWithArgs = "$route/{$accountTypeArg}"
    val arguments = listOf(
        navArgument(accountTypeArg) { type = NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "rally://$route/{$accountTypeArg}" }
    )
}
object SingleBill : MyDestination {
    override val icon = Icons.Filled.Info
    override val route = "single_bill"
    const val billTypeArg = "bill_type"
    val routeWithArgs = "$route/{$billTypeArg}"
    val arguments = listOf(
        navArgument(billTypeArg) { type = NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "rally://$route/{$billTypeArg}" }
    )
}
val allDestinations = listOf<MyDestination>(Home, Setting, Profile, SingleAccount, SingleBill)