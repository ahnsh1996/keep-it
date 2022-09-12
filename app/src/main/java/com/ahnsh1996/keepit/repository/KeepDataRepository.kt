package com.ahnsh1996.keepit.repository

import com.ahnsh1996.keepit.model.KeepData
import java.util.*

class KeepDataRepository(private val keepDataLocalDataSource: KeepDataLocalDataSource) {
    suspend fun addKeepData(keepData: KeepData) {
        keepDataLocalDataSource.addKeepData(keepData)
    }

    suspend fun deleteKeepData(idList: List<UUID>) {
        keepDataLocalDataSource.deleteKeepData(idList)
    }

    suspend fun getKeepDataCount() = keepDataLocalDataSource.getKeepDataCount()

    suspend fun getAllKeepData() = keepDataLocalDataSource.getAllKeepData()

    suspend fun searchKeepData(keyword: String) = keepDataLocalDataSource.searchKeepData(keyword)

    companion object {
        private var instance: KeepDataRepository? = null

        fun getInstance(keepDataLocalDataSource: KeepDataLocalDataSource): KeepDataRepository {
            return instance ?: KeepDataRepository(keepDataLocalDataSource).also { instance = it }
        }
    }
}