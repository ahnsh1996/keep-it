package com.ahnsh1996.keepit.repository

import com.ahnsh1996.keepit.model.KeepData
import java.util.*

interface KeepDataSource {

    suspend fun addKeepData(keepData: KeepData)

    suspend fun getKeepData(): List<KeepData>

    suspend fun deleteKeepData(idList: List<UUID>)

    suspend fun searchKeepData(keyword: String): List<KeepData>

    suspend fun getKeepDataCount(): Int
}