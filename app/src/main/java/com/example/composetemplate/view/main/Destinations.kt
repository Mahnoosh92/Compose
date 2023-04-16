package com.example.composetemplate.view.main

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

object Favorite : MyDestination {
    override val icon = Icons.Filled.Favorite
    override val route = "favorite"
}

object Setting : MyDestination {
    override val icon = Icons.Filled.Settings
    override val route = "setting"
}

object SingleDetails : MyDestination {
    override val icon = Icons.Filled.Info
    override val route = "single_detail"
    const val detailTypeArg = "detail_type"
    val routeWithArgs = "$route/{$detailTypeArg}"
    val arguments = listOf(
        navArgument(detailTypeArg) { type = NavType.StringType }
    )
//    val deepLinks = listOf(
//        navDeepLink { uriPattern = "rally://$route/{$routeWithArgs}" }
//    )
}
