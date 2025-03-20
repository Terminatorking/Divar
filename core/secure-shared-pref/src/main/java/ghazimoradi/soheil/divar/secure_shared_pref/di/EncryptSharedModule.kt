package ghazimoradi.soheil.divar.secure_shared_pref.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EncryptSharedModule {

    @Provides
    @Singleton
    fun provideEncryptSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {

        val masterKey = MasterKey.Builder(
            context,
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        ).setKeyScheme(
            MasterKey.KeyScheme.AES256_GCM
        ).build()

        return EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }
}