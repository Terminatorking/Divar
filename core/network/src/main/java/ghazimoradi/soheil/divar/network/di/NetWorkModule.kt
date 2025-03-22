package ghazimoradi.soheil.divar.network.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.divar.network.BuildConfig
import ghazimoradi.soheil.divar.network.constant.BASE_URL
import ghazimoradi.soheil.divar.network.preferences.TokenPreferences
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenPreferences: TokenPreferences): OkHttpClient {

        val http = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        http.addInterceptor(
            Interceptor { chain: Interceptor.Chain ->

                val original = chain.request()
                val request = original.newBuilder().header(
                    name = "Content-Type",
                    value = "application/json",
                ).header(
                    name = "Authorization", value = tokenPreferences.readToken()
                ).method(
                    method = original.method, body = original.body
                ).build()

                chain.proceed(request = request)

            }
        )

        val logInterceptor = HttpLoggingInterceptor(
            logger = {
                if (BuildConfig.DEBUG) {
                    Log.d("Retrofit", it)
                }
            },
        )

        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        http.addInterceptor(logInterceptor)
        return http.build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, json: Json, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(
                json.asConverterFactory(
                    contentType = "application/json".toMediaType()
                )
            ).build()
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return BASE_URL
    }

}