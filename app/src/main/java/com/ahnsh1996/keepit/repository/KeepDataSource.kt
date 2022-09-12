package com.ahnsh1996.keepit.repository

import com.ahnsh1996.keepit.model.KeepData
import kotlinx.coroutines.flow.Flow
import java.util.*

interface KeepDataSource {

    suspend fun addKeepData(keepData: KeepData)

    suspend fun deleteKeepData(idList: List<UUID>)

    suspend fun getKeepDataCount(): Int

    suspend fun getAllKeepData(): Flow<List<KeepData>>

    suspend fun searchKeepData(keyword: String): Flow<List<KeepData>>
}