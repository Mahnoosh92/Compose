package com.example.composetemplate.view.dashboard.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composetemplate.data.Account
import com.example.composetemplate.data.Bill
import com.example.composetemplate.data.UserData
import com.example.composetemplate.view.base.BaseRow
import com.example.composetemplate.view.base.BaseTabBar
import com.example.composetemplate.view.base.TabScreen

@Composable
fun HomeScreen(
    tabs: List<TabScreen>,
    selectedTab: TabScreen,
    onTabClicked: (TabScreen) -> Unit,
    onAccountClicked: (Account) -> Unit,
    onBillClicked: (Bill) -> Unit
) {
    Column() {
        BaseTabBar(
            tabScreens = tabs,
            selectedTab = selectedTab,
            onTabClicked = {
                onTabClicked(it)
            })
        Spacer(modifier = Modifier.height(10.dp))
        when (selectedTab) {
            tabs[0] -> {
                AccountTabContent(
                    accounts = UserData.accounts,
                    onAccountClicked = { onAccountClicked(it) })
            }
            tabs[1] -> {
                BillsTabContent(bills = UserData.bills, onBillClicked = { onBillClicked(it) })
            }
        }
    }
}

@Composable
fun AccountTabContent(
    modifier: Modifier = Modifier,
    accounts: List<Account>,
    onAccountClicked: (Account) -> Unit
) {
    LazyColumn(state = rememberLazyListState()) {
        items(accounts) {
            Row(modifier = modifier.clickable(onClick = { onAccountClicked(it) })) {
                BaseRow(color = it.color) {
                    Column() {
                        Text(text = it.name, style = MaterialTheme.typography.body1)
                        Spacer(modifier = modifier.height(10.dp))
                        Text(text = it.balance.toString(), style = MaterialTheme.typography.subtitle1)
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
            }
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
                        Text(text = it.amount.toString(), style = MaterialTheme.typography.subtitle1)
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
            }
        }
    }
}