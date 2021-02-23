package com.example.login3.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "networks")
data class Network(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val network: String = "",
    val login: String = "",
    val password: String = "",
    val time: String = "",
    val day: String = "",
    val month: String = ""
)