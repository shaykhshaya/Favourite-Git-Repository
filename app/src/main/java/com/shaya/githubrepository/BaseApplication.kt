package com.shaya.githubrepository

import android.app.Application
import com.shaya.githubrepository.data.RoomItemDatabase

class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
        lateinit var database: RoomItemDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = RoomItemDatabase.getDatabase(this)
    }

}