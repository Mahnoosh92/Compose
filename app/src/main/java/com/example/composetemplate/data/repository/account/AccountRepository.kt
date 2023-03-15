package com.example.composetemplate.data.repository.account

import com.example.composetemplate.data.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getAccounts(): Flow<List<Account>>
}