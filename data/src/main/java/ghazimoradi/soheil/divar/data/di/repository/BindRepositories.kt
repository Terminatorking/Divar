package ghazimoradi.soheil.divar.data.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.divar.data.repository.ads.AdsSummaryRepositoryImpl
import ghazimoradi.soheil.divar.data.repository.category.CategoryRepositoryImpl
import ghazimoradi.soheil.divar.domain.repositories.ads.AdsSummaryRepository
import ghazimoradi.soheil.divar.domain.repositories.category.CategoryRepository

@Module
@InstallIn(SingletonComponent::class)
interface BindRepositories {

    @Binds
    fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    fun bindAdsSummary(repo: AdsSummaryRepositoryImpl): AdsSummaryRepository
//
//    @Binds
//    fun bindLocation(repo: LocationRepositoryImpl): LocationRepository
//
//    @Binds
//    fun bindCategoryOfAds(repo: CategoryOfAdsRepositoryImpl): CategoryOfAdsRepository
//
//    @Binds
//    fun bindParameterRepository(repo: ParameterRepositoryImpl): ParameterRepository
//
//    @Binds
//    fun bindFilterRepo(repo: FilterRepositoryImpl): FilterRepository
//
//    @Binds
//    fun bindAdsRepo(repo: AdsRepositoryImpl): AdsRepository
//
//    @Binds
//    fun bindUserRepo(repo: UserRepositoryImpl): UserRepository

}