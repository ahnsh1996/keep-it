package com.ahnsh1996.keepit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahnsh1996.keepit.model.KeepData
import java.util.*

@Dao
interface KeepDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keepData: KeepData)

    @Query("DELETE FROM KeepData WHERE id in (:idList)")
    suspend fun deleteData(idList: List<UUID>)

    @Query("SELECT * FROM KeepData")
    suspend fun getAllDataList(): List<KeepData>

    @Query("SELECT * FROM KeepData WHERE data LIKE '%' || :keyword || '%'")
    suspend fun searchData(keyword: String): List<KeepData>

    @Query("SELECT COUNT(id) FROM KeepData")
    suspend fun getDataCount(): Int
}