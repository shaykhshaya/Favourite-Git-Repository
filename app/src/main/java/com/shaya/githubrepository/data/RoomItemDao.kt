package com.shaya.githubrepository.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomItemDao {

    @Query("SELECT * FROM item_database ORDER BY id")
    fun getItems(): Flow<List<RoomItem>>

    @Query("SELECT * FROM item_database WHERE id = :id")
    fun getItem(id: Int): Flow<RoomItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RoomItem)

}