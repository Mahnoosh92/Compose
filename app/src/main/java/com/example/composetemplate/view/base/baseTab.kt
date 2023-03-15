package com.example.composetemplate.view.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetemplate.ui.theme.ComposeTemplateTheme

@Composable
fun BaseTabBar(
    modifier: Modifier = Modifier,
    tabScreens: List<TabScreen>,
    selectedTab: TabScreen,
    onTabClicked: (TabScreen) -> Unit
) {
    TabRow(selectedTabIndex = tabScreens.indexOf(selectedTab)) {
        tabScreens.forEach {
            Tab(selected = it.selected, onClick = { onTabClicked(it) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
                    Text(text = it.tabText)
                    Spacer(modifier = modifier.height(10.dp))
                    Icon(imageVector = it.icon, contentDescription = it.tabText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBaseTabBar() {
    val tabs = listOf<TabScreen>(
        TabScreen("Account", true, Icons.Filled.AccountCircle),
        TabScreen("Bills", false, Icons.Filled.Info)
    )
    ComposeTemplateTheme() {
        BaseTabBar(tabScreens = tabs, selectedTab = tabs[0], onTabClicked = {})
    }
}

data class TabScreen(val tabText: String, val selected: Boolean, val icon: ImageVector)

