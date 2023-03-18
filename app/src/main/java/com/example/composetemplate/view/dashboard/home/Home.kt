package com.example.composetemplate.view.dashboard.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composetemplate.data.Account
import com.example.composetemplate.data.Bill
import com.example.composetemplate.data.UserData
import com.example.composetemplate.ui.theme.Shapes
import com.example.composetemplate.view.base.BaseRow
import com.example.composetemplate.view.base.BaseTabBar
import com.example.composetemplate.view.base.TabScreen

@Composable
fun HomeScreen(
    modifier: Modifier,
    tabs: List<TabScreen>,
    selectedTab: TabScreen,
    onTabClicked: (TabScreen) -> Unit,
    onAccountClicked: (Account) -> Unit,
    onBillClicked: (Bill) -> Unit,
    onFABClicked: () -> Unit
) {
    val state = rememberLazyListState()
    val showButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }
    Scaffold(topBar = {
        BaseTabBar(
            tabScreens = tabs,
            selectedTab = selectedTab,
            onTabClicked = {
                onTabClicked(it)
            })
    }, floatingActionButton = {
        FAB( visibility =showButton, onFABClicked = { onFABClicked() })
    }
    ) { paddings ->
        Spacer(modifier = Modifier.height(10.dp))
        when (selectedTab) {
            tabs[0] -> {
                AccountTabContent(
                    modifier = modifier.padding(paddings),
                    accounts = UserData.accounts,
                    onAccountClicked = { onAccountClicked(it) }, state = state
                )

            }
            tabs[1] -> {
                BillsTabContent(
                    modifier = modifier.padding(paddings),
                    bills = UserData.bills,
                    onBillClicked = { onBillClicked(it) }
                )
            }
        }

    }
}

@Composable
fun AccountTabContent(
    modifier: Modifier = Modifier,
    accounts: List<Account>,
    onAccountClicked: (Account) -> Unit,
    state: LazyListState
) {
    LazyColumn(state = state) {
        items(accounts) {
            Row(modifier = modifier.clickable(onClick = { onAccountClicked(it) })) {
                BaseRow(color = it.color) {
                    Column() {
                        Text(text = it.name, style = MaterialTheme.typography.body1)
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = it.balance.toString(),
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
            }
        }
    }


}

@Composable
fun FAB(modifier: Modifier=Modifier, visibility: Boolean, onFABClicked: () -> Unit) {
    AnimatedVisibility(visible = visibility) {
        FloatingActionButton(onClick = { onFABClicked() }, shape = CircleShape) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                modifier = modifier.size(30.dp, 30.dp)
            )
        }
    }
}

@Composable
fun BillsTabContent(
    modifier: Modifier = Modifier,
    bills: List<Bill>,
    onBillClicked: (Bill) -> Unit
) {
    LazyColumn(state = rememberLazyListState()) {
        items(bills) {
            Row(modifier = modifier.clickable(onClick = { onBillClicked(it) })) {
                BaseRow(color = it.color) {
                    Column() {
                        Text(text = it.name, style = MaterialTheme.typography.body1)
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = it.amount.toString(),
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
                Spacer(modifier = modifier.height(10.dp))

            }
        }
    }
}