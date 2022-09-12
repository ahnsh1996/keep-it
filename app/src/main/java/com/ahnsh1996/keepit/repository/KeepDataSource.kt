package com.ahnsh1996.keepit.repository

import androidx.paging.PagingSource
import com.ahnsh1996.keepit.model.KeepData
import java.util.*

interface KeepDataSource {

    suspend fun addKeepData(keepData: KeepData)

    suspend fun deleteKeepData(idList: List<UUID>)

    suspend fun getKeepDataCount(): Int

    fun getAllKeepData(): PagingSource<Int, KeepData>

    fun searchKeepData(keyword: String): PagingSource<Int, KeepData>
}