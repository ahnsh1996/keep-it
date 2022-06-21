package com.ahnsh1996.keepit.repository

import com.ahnsh1996.keepit.database.KeepDataDao
import com.ahnsh1996.keepit.model.KeepData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class KeepDataLocalDataSource(
    private val keepDataDao: KeepDataDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : KeepDataSource {

    override suspend fun addKeepData(keepData: KeepData) {
        withContext(ioDispatcher) {
            keepDataDao.insert(keepData)
        }
    }

    override suspend fun getKeepData(): List<KeepData> =
        withContext(ioDispatcher) {
            keepDataDao.getAllDataList()
        }

    override suspend fun deleteKeepData(idList: List<UUID>) {
        withContext(ioDispatcher) {
            keepDataDao.deleteData(idList)
        }
    }

    override suspend fun searchKeepData(keyword: String): List<KeepData> =
        withContext(ioDispatcher) {
            keepDataDao.searchData(keyword)
        }

    override suspend fun getKeepDataCount(): Int =
        withContext(ioDispatcher) {
            keepDataDao.getDataCount()
        }
}