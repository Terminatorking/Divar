package ghazimoradi.soheil.divar.data.di.bindRepositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.divar.data.repository.ads.AdsRepositoryImpl
import ghazimoradi.soheil.divar.data.repository.ads.AdsSummaryRepositoryImpl
import ghazimoradi.soheil.divar.data.repository.category.CategoryOfAdsRepositoryImpl
import ghazimoradi.soheil.divar.data.repository.category.CategoryRepositoryImpl
import ghazimoradi.soheil.divar.data.repository.filter.FilterRepositoryImpl
import ghazimoradi.soheil.divar.data.repository.location.LocationRepositoryImpl
import ghazimoradi.soheil.divar.data.repository.parameter.ParameterRepositoryImpl
import ghazimoradi.soheil.divar.data.repository.user.UserRepositoryImpl
import ghazimoradi.soheil.divar.domain.repositories.ads.AdsRepository
import ghazimoradi.soheil.divar.domain.repositories.ads.AdsSummaryRepository
import ghazimoradi.soheil.divar.domain.repositories.category.CategoryOfAdsRepository
import ghazimoradi.soheil.divar.domain.repositories.category.CategoryRepository
import ghazimoradi.soheil.divar.domain.repositories.filter.FilterRepository
import ghazimoradi.soheil.divar.domain.repositories.location.LocationRepository
import ghazimoradi.soheil.divar.domain.repositories.parameter.ParameterRepository
import ghazimoradi.soheil.divar.domain.repositories.user.UserRepository

@Module
@InstallIn(SingletonComponent::class)
interface BindRepositories {

    @Binds
    fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    fun bindAdsSummary(repo: AdsSummaryRepositoryImpl): AdsSummaryRepository

    @Binds
    fun bindLocation(repo: LocationRepositoryImpl): LocationRepository

    @Binds
    fun bindCategoryOfAds(repo: CategoryOfAdsRepositoryImpl): CategoryOfAdsRepository

    @Binds
    fun bindParameterRepository(repo: ParameterRepositoryImpl): ParameterRepository

    @Binds
    fun bindFilterRepo(repo: FilterRepositoryImpl): FilterRepository

    @Binds
    fun bindAdsRepo(repo: AdsRepositoryImpl): AdsRepository

    @Binds
    fun bindUserRepo(repo: UserRepositoryImpl): UserRepository

}