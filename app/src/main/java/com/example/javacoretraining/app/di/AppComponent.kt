package com.example.javacoretraining.app.di

import androidx.fragment.app.Fragment
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(fragment: Fragment)
}
