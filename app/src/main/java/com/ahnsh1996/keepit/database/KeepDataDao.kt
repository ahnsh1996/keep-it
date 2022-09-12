package com.ahnsh1996.keepit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahnsh1996.keepit.model.KeepData
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface KeepDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keepData: KeepData)

    @Query("DELETE FROM KeepData WHERE id in (:idList)")
    suspend fun deleteData(idList: List<UUID>)

    @Query("SELECT COUNT(id) FROM KeepData")
    suspend fun getDataCount(): Int

    @Query("SELECT * FROM KeepData")
    fun getAllData(): Flow<List<KeepData>>

    @Query("SELECT * FROM KeepData WHERE data LIKE '%' || :keyword || '%'")
    fun searchData(keyword: String): Flow<List<KeepData>>
}