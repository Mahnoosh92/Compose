package com.example.composetemplate.data.repository.account

import com.example.composetemplate.data.Account
import com.example.composetemplate.data.UserData
import com.example.composetemplate.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(@IoDispatcher private val ioDispatcher: CoroutineDispatcher) :
    AccountRepository {
    override suspend fun getAccounts(): Flow<List<Account>> = withContext(ioDispatcher) {
        flow {
            emit(UserData.accounts)
        }
    }
}