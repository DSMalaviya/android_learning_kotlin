package com.example.sevenminworkout.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistryDao {
    @Insert
    suspend fun insert(histryEntity: HistryEntity)

    @Query("select * from `history-table`")
    fun fetchAllDates():Flow<List<HistryEntity>>
}