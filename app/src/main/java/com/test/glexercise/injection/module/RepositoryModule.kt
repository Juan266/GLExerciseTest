package com.test.glexercise.injection.module


import com.test.glexercise.data.repository.MainListRepositoryImpl
import com.test.glexercise.domain.repository.MainListRepository
import org.koin.dsl.module

val RepositoryModule = module {

    single<MainListRepository> { MainListRepositoryImpl(get()) }
}