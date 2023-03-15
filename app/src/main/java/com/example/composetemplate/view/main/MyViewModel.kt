package com.example.composetemplate.view.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composetemplate.view.base.TabScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {
    private val mutableTabsList = mutableStateListOf<TabScreen>(
        TabScreen(
            tabText = "Account",
            selected = true,
            icon = Icons.Filled.AccountCircle
        ), TabScreen(tabText = "Bills", selected = false, icon = Icons.Filled.Info)
    )
    val tabsList: List<TabScreen> = mutableTabsList

    fun updateTabs(tabScreen: TabScreen) {
        mutableTabsList.forEach { tab ->
            tab.copy(selected = tab.tabText == tabScreen.tabText)
        }

    }
}