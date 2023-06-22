package com.example.clear.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//modules are used to add bindings to hilt
@InstallIn(SingletonComponent::class) // this will not be recreated , just one instance
@Module
object AppModule {
}