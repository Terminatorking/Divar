package ghazimoradi.soheil.divar.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.divar.network.api.ads.AdsApiService
import ghazimoradi.soheil.divar.network.api.ads.AdsSummaryApiService
import ghazimoradi.soheil.divar.network.api.category.CategoryApiService
import ghazimoradi.soheil.divar.network.api.location.LocationApiService
import ghazimoradi.soheil.divar.network.api.category.CategoryOfAdsApiService
import ghazimoradi.soheil.divar.network.api.parameter.ParameterApiService
import ghazimoradi.soheil.divar.network.api.user.UserApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideApiServices {
    @Singleton
    @Provides
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiService {
        return retrofit.create(CategoryApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAdsSummaryApiService(retrofit: Retrofit): AdsSummaryApiService {
        return retrofit.create(AdsSummaryApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationApiService(retrofit: Retrofit): LocationApiService {
        return retrofit.create(LocationApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryOfAdsApiService(retrofit: Retrofit): CategoryOfAdsApiService {
        return retrofit.create(CategoryOfAdsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideParametersApiService(retrofit: Retrofit): ParameterApiService {
        return retrofit.create(ParameterApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAdsApiService(retrofit: Retrofit): AdsApiService {
        return retrofit.create(AdsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }
}