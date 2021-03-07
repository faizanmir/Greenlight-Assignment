package com.example.sunking_greenlight_assignment.di.main

import android.content.Context
import com.example.sunking_greenlight_assignment.data.local.database.AppDatabase
import com.example.sunking_greenlight_assignment.network.Api
import com.example.sunking_greenlight_assignment.repositories.MainRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
open class MainModule {

    //Better to make Dagger scopes and make this last only until main activity exists

    @Provides
    fun provideMainRepository(
        api: Api?,
        appDatabase: AppDatabase,
        context: Context
    ): MainRepository {
        return MainRepository(api, appDatabase, context)
    }

    @Provides
    open fun provideMainApi(retrofit: Retrofit): Api? {
        return retrofit.create(Api::class.java)
    }

}