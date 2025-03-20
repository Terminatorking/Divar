package ghazimoradi.soheil.divar.data.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.divar.data.repository.CategoryRepositoryImpl
import ghazimoradi.soheil.divar.domain.repositories.CategoryRepository

@Module
@InstallIn(SingletonComponent::class)
interface BindRepositories {

    @Binds
    fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

}