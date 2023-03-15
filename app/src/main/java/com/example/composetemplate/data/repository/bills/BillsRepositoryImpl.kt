package com.example.composetemplate.data.repository.bills

import com.example.composetemplate.data.Bill
import com.example.composetemplate.data.UserData
import com.example.composetemplate.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BillsRepositoryImpl @Inject constructor(@IoDispatcher private val ioDispatcher: CoroutineDispatcher) :
    BillsRepository {
    override suspend fun getBills(): Flow<List<Bill>> = withContext(ioDispatcher) {
        flow {
            emit(UserData.bills)
        }
    }
}