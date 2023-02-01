package com.shaya.githubrepository.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_database")
data class RoomItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name= "name")
    val name: String,
    @ColumnInfo(name= "owner")
    val owner: String,
    @ColumnInfo(name= "description")
    val description: String,
    @ColumnInfo(name= "url")
    val url: String
) {
}