package com.example.login3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.login3.entity.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Network::class], version = 1)
abstract class NetDatabase : RoomDatabase() {

    abstract fun netDao(): NetDao

    companion object {
        @Volatile
        private var INSTANCE: NetDatabase? = null

        fun getDatabase(context: Context): NetDatabase? {
            if (INSTANCE == null) {
                synchronized(NetDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        NetDatabase::class.java,
                        "network-database"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}