package com.example.login3.room

import android.content.Context
import com.example.login3.repo.NetRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Provides
    fun provideWordsDao(@ApplicationContext appContext: Context): NetDao {
        return NetDatabase.getDatabase(appContext)!!.netDao()
    }

    @Provides
    fun provideWordsDBRepository(netDao: NetDao) =
        NetRepo(netDao)
}