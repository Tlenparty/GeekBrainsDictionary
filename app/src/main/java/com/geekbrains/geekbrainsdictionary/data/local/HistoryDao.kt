package com.geekbrains.geekbrainsdictionary.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.geekbrains.geekbrainsdictionary.data.local.model.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT* from HistoryEntity")
    suspend fun all():List<HistoryEntity>

    @Insert(onConflict = IGNORE)
    suspend fun insert(entity: HistoryEntity)

    @Update
    suspend fun update(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)
}