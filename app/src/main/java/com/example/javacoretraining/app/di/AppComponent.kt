package com.example.javacoretraining.app.di

import androidx.lifecycle.AndroidViewModel
import dagger.Component

@Component(modules = [DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(viewModel: AndroidViewModel)
}
