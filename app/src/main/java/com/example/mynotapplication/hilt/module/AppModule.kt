package com.example.mynotapplication.hilt.module

import android.app.Application
import androidx.room.Room
import com.example.mynotapplication.database.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application):AppRoomDatabase =
        Room.databaseBuilder(application,AppRoomDatabase::class.java,"noteTable")
            .build()
}