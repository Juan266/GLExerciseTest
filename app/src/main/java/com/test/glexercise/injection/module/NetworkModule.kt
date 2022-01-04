package com.test.glexercise.injection.module

import com.test.glexercise.data.remote.ListService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT_CONNECT = 10
const val TIMEOUT_WRITE = 10
const val TIMEOUT_READ = 30

@JvmField
val NetworkModule = module {

    val baseURL = "http://private-f0eea-mobilegllatam.apiary-mock.com"

    fun provideApi(retrofit: Retrofit): ListService {
        return retrofit.create(ListService::class.java)
    }

    fun provideInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    single { provideApi(get()) }
    single { provideInterface() }

}

fun getOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(getHttpLoggingInterceptor())
    builder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
    builder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
    builder.writeTimeout(TIMEOUT_WRITE.toLong(), TimeUnit.SECONDS)
    return builder.build()
}

private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}
