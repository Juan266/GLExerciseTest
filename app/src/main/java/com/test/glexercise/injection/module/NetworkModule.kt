package com.test.glexercise.injection.module

import com.test.glexercise.data.remote.ListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val TIMEOUT_CONNECT = 10
const val TIMEOUT_WRITE = 10
const val TIMEOUT_READ = 30

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule  {

    val baseURL = "http://private-f0eea-mobilegllatam.apiary-mock.com"

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ListService {
        return retrofit.create(ListService::class.java)
    }

    @Singleton
    @Provides
    fun provideInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(getHttpLoggingInterceptor())
        builder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(TIMEOUT_WRITE.toLong(), TimeUnit.SECONDS)
        return builder.build()
    }

    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }



}

