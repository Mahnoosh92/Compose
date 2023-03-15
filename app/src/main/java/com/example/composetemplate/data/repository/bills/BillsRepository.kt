package com.example.composetemplate.data.repository.bills

import com.example.composetemplate.data.Bill
import kotlinx.coroutines.flow.Flow

interface BillsRepository {
    suspend fun getBills(): Flow<List<Bill>>
}