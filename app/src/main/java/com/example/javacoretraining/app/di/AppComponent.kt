package com.example.javacoretraining.app.di

import android.app.Activity
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(app: Activity)
}
