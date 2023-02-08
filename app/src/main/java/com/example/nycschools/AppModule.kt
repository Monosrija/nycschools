package com.example.nycschools

import com.example.nycschools.domain.NycSchoolsApiService
import com.example.nycschools.domain.SchoolUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUsecase() : SchoolUsecase {
        return SchoolUsecase(provideApiService(provideRetrofit(BASE_URL = baseUrl)))
    }

    @Singleton
    @Provides
    fun provideRetrofit(BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NycSchoolsApiService = retrofit.create(
        NycSchoolsApiService::class.java)

    companion object {
        const val baseUrl =
            "https://data.cityofnewyork.us/resource/"
    }

}