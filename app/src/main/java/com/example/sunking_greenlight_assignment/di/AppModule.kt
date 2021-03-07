package com.example.sunking_greenlight_assignment.di

import android.content.Context
import androidx.room.Room
import com.example.sunking_greenlight_assignment.BaseApplication
import com.example.sunking_greenlight_assignment.data.dataManager.DataManager
import com.example.sunking_greenlight_assignment.data.dataManager.DataManagerImpl
import com.example.sunking_greenlight_assignment.data.local.database.AppDatabase
import com.example.sunking_greenlight_assignment.data.local.database.DbManager
import com.example.sunking_greenlight_assignment.data.local.database.DbManagerImpl
import com.example.sunking_greenlight_assignment.data.local.prefs.PreferenceManager
import com.example.sunking_greenlight_assignment.data.local.prefs.PreferenceManagerImpl
import com.example.sunking_greenlight_assignment.data.remote.ApiManager
import com.example.sunking_greenlight_assignment.data.remote.ApiManagerImpl
import com.example.sunking_greenlight_assignment.network.NetworkConnectionInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val BASE_URL = "http://demo1929804.mockable.io/"
    }

    @Provides
    @Singleton
    fun provideContext(application: BaseApplication): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideHttpCache(application: BaseApplication): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache?,context: Context?): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context!!))
            .cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson?, okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson!!))
            .baseUrl(BASE_URL)
            .client(okHttpClient!!)
            .build()
    }

    @Provides
    fun provideDataManager(appDataManager: DataManagerImpl): DataManager {
        return appDataManager
    }


    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: DbManagerImpl): DbManager {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideAppDatabase(dbName: String, context: Context?): AppDatabase {
        return Room.databaseBuilder(context!!, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideDatabaseName(): String {
        return "App Db"
    }

    @Provides
    @Singleton
    fun providesApiManager(apiManager: ApiManagerImpl): ApiManager {
        return apiManager
    }


    @Provides
    @Singleton
    fun providesPrefsManager(preferenceManagerImpl: PreferenceManagerImpl): PreferenceManager {
        return preferenceManagerImpl;
    }
}