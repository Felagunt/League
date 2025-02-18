package com.example.league

import android.app.Application
import com.example.league.di.initKoin
import com.example.league.di.ktorModule
import com.example.league.di.module

class LeagueApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            modules(module, ktorModule)
        }
    }
}