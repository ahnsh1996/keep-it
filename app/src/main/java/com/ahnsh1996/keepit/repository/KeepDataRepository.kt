package com.ahnsh1996.keepit.repository

import com.ahnsh1996.keepit.model.KeepData
import java.util.*

class KeepDataRepository(private val keepDataLocalDataSource: KeepDataLocalDataSource) {
    suspend fun addKeepData(keepData: KeepData) {
        keepDataLocalDataSource.addKeepData(keepData)
    }

    suspend fun getKeepData(): List<KeepData> = keepDataLocalDataSource.getKeepData()

    suspend fun deleteKeepData(idList: List<UUID>) {
        keepDataLocalDataSource.deleteKeepData(idList)
    }

    suspend fun searchKeepData(keyword: String): List<KeepData> = keepDataLocalDataSource.searchKeepData(keyword)

    suspend fun getKeepDataCount() = keepDataLocalDataSource.getKeepDataCount()

    companion object {
        private var instance: KeepDataRepository? = null

        fun getInstance(keepDataLocalDataSource: KeepDataLocalDataSource): KeepDataRepository {
            return instance ?: KeepDataRepository(keepDataLocalDataSource).also { instance = it }
        }
    }
}