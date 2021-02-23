package com.example.login3.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.login3.entity.Network

@Dao
interface NetDao {

    @Query("SELECT * from networks")
    fun getNetworks(): LiveData<List<Network>>

    @Update
    suspend fun updateNetwork(network: Network)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setNetwork(network: Network)

    @Delete
    suspend fun deleteNetworks(network: Network)
}