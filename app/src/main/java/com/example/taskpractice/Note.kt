package com.example.taskpractice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "text")
    var text: String,
    @ColumnInfo(name = "timestamp")
    var timesTamp: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}