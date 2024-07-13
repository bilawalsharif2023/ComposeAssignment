package com.example.composetask.di


import com.example.composetask.remote.APIServices
import com.example.composetask.utility.APIS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(APIS.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): APIServices {

        return retrofit.create(APIServices::class.java)
    }
}