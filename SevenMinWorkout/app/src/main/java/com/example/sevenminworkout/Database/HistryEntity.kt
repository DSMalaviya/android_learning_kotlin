package com.example.sevenminworkout.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history-table")
data class HistryEntity(
    @PrimaryKey
    val Date:String
)
