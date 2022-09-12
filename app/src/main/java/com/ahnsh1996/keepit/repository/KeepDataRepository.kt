package com.ahnsh1996.keepit.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahnsh1996.keepit.common.PAGING_SIZE
import com.ahnsh1996.keepit.model.KeepData
import kotlinx.coroutines.flow.Flow
import java.util.*

class KeepDataRepository(private val keepDataLocalDataSource: KeepDataLocalDataSource) {
    suspend fun addKeepData(keepData: KeepData) {
        keepDataLocalDataSource.addKeepData(keepData)
    }

    suspend fun deleteKeepData(idList: List<UUID>) {
        keepDataLocalDataSource.deleteKeepData(idList)
    }

    suspend fun getKeepDataCount() = keepDataLocalDataSource.getKeepDataCount()

    fun getAllKeepData(): Flow<PagingData<KeepData>> {
        val pagingSourceFactory = { keepDataLocalDataSource.getAllKeepData() }

        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchKeepData(keyword: String): Flow<PagingData<KeepData>> {
        val pagingSourceFactory = { keepDataLocalDataSource.searchKeepData(keyword) }

        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        private var instance: KeepDataRepository? = null

        fun getInstance(keepDataLocalDataSource: KeepDataLocalDataSource): KeepDataRepository {
            return instance ?: KeepDataRepository(keepDataLocalDataSource).also { instance = it }
        }
    }
}