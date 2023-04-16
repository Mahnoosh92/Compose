package com.example.composetemplate.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetemplate.R
import com.example.composetemplate.data.models.local.BottomItem
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import com.example.composetemplate.utils.navigateSingleTopTo
import com.example.composetemplate.view.detail.DetailScreen
import com.example.composetemplate.view.detail.DetailViewModel
import com.example.composetemplate.view.favorite.FavoriteScreen
import com.example.composetemplate.view.favorite.FavoriteViewModel
import com.example.composetemplate.view.home.HomeScreen
import com.example.composetemplate.view.home.HomeViewModel
import com.example.composetemplate.view.setting.SettingScreen
import kotlinx.coroutines.launch

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    var bottomVisibility by remember {
        mutableStateOf<Boolean>(true)
    }

    val bottomItems = listOf(
        BottomItem("Home", Icons.Filled.Home, "home"),
        BottomItem("Favorite", Icons.Filled.Favorite, "favorite"),
        BottomItem("Setting", Icons.Filled.Settings, "setting")
    )

    Scaffold(modifier = modifier, scaffoldState = scaffoldState, topBar = {
        TopBar() {
            if (scaffoldState.drawerState.isOpen) {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            } else {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        }
    },
        drawerContent = { DrawerContent() },
        bottomBar = {
            BottomNavigation(
                navController = navController,
                bottomItems = bottomItems,
                visibility = bottomVisibility
            )
        }) { paddings ->
        MyNavHost(
            navHostController = navController, modifier = Modifier.padding(paddings)
        ) {
            bottomVisibility = it
        }
    }
}

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    changeVisibility: (Boolean) -> Unit
) {
    NavHost(navController = navHostController, startDestination = Home.route) {
        composable(route = Home.route) {
            HomeScreen(viewModel = hiltViewModel<HomeViewModel>())
        }
        composable(route = Setting.route) {
            SettingScreen()
        }
        composable(route = Favorite.route) {
            FavoriteScreen(viewModel = hiltViewModel<FavoriteViewModel>()) {
                navHostController.navigate("${SingleDetails.route}/$it")
            }
        }
        composable(
            route = SingleDetails.routeWithArgs, arguments = SingleDetails.arguments
        ) { navBackStackEntry ->
            changeVisibility(false)
            val detailType = navBackStackEntry.arguments?.getString(SingleDetails.detailTypeArg)
            DetailScreen(detailType ?: "", viewModel = hiltViewModel<DetailViewModel>())
        }
    }
}

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    bottomItems: List<BottomItem>,
    navController: NavHostController,
    visibility: Boolean
) {
    if (visibility) {
        BottomNavigation(modifier = modifier) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            bottomItems.forEach {
                BottomNavigationItem(selected = it.route == currentRoute,
                    onClick = { navController.navigateSingleTopTo(it.route) },
                    icon = {
                        Icon(imageVector = it.icon, contentDescription = it.title)
                    })
            }
        }
    }
}

@Composable
fun DrawerContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape)
        ) {
            Image(
                modifier = modifier.matchParentSize(),
                painter = painterResource(id = R.drawable.account),
                contentDescription = null
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        // TODO:ADDING NEW FEATURES
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawerContent() {
    ComposeTemplateTheme {
        DrawerContent()
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier, onClickHamburger: () -> Unit) {
    TopAppBar(title = {
        Text(text = "Jetpack Compose")
    }, navigationIcon = {
        IconButton(onClick = { onClickHamburger() }) {
            Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer Icon")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    ComposeTemplateTheme {
        TopBar() {}
    }
}