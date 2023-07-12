package com.example.javacoretraining.app

import android.app.Application
import com.example.javacoretraining.app.di.AppComponent
import com.example.javacoretraining.app.di.DaggerAppComponent
import com.example.javacoretraining.app.di.DataModule

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .dataModule(DataModule(this))
            .build()
    }
}
