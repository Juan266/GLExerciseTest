package com.test.glexercise.injection.module

import com.test.glexercise.data.repository.MainListRepositoryImpl
import com.test.glexercise.domain.repository.MainListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    internal fun provideMainListRepository(repository: MainListRepositoryImpl): MainListRepository {
        return repository
    }
}