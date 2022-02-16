package com.example.mynotapplication.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@HiltAndroidApp
class HiltApp : Application(){
}