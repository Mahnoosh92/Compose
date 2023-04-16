package com.example.composetemplate.di

import com.example.composetemplate.BuildConfig
import com.example.composetemplate.data.api.ApiService
import com.example.composetemplate.data.datasource.remote.coin.market.DefaultRemoteCoinMarketDataSource
import com.example.composetemplate.data.datasource.remote.coin.market.RemoteCoinMarketDataSource
import com.example.composetemplate.data.datasource.remote.exchange.DefaultRemoteExchangeDataSource
import com.example.composetemplate.data.datasource.remote.exchange.RemoteExchangeDataSource
import com.example.composetemplate.data.datasource.remote.trend.DefaultRemoteTrendDataSource
import com.example.composetemplate.data.datasource.remote.trend.RemoteTrendDataSource
import com.example.composetemplate.data.repository.coin.market.CoinMarketRepository
import com.example.composetemplate.data.repository.coin.market.DefaultCoinMarketRepository
import com.example.composetemplate.data.repository.exchange.DefaultExchangeRepository
import com.example.composetemplate.data.repository.exchange.ExchangeRepository
import com.example.composetemplate.data.repository.trend.DefaultTrendRepository
import com.example.composetemplate.data.repository.trend.TrendRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true).followSslRedirects(true)
//            .addInterceptor { chain ->
//                val newRequest =
//                    chain.request().newBuilder().addHeader("x-api-key", BuildConfig.API_KEY).build()
//                chain.proceed(newRequest)
//            }
        return if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor).build()
        } else {
            builder.build()
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL).client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
//        appContext, MyAppDataBase::class.java, "item_database"
//    )
//        .fallbackToDestructiveMigration()
//        .build()
}

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatchersModule {

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindRemoteTrendDataSource(
        defaultRemoteTrendDataSource: DefaultRemoteTrendDataSource
    ): RemoteTrendDataSource

    @Binds
    abstract fun bindRemoteExchangeDataSource(
        defaultRemoteExchangeDataSource: DefaultRemoteExchangeDataSource
    ): RemoteExchangeDataSource

    @Binds
    abstract fun bindRemoteCoinMarketDataSource(
        defaultRemoteCoinMarketDataSource: DefaultRemoteCoinMarketDataSource
    ): RemoteCoinMarketDataSource
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTrendRepository(
        defaultRemoteTrendDataSource: DefaultTrendRepository
    ): TrendRepository

    @Binds
    abstract fun bindExchangeRepository(
        defaultExchangeRepository: DefaultExchangeRepository
    ): ExchangeRepository

    @Binds
    abstract fun bindCoinMarketRepository(
        defaultCoinMarketRepository: DefaultCoinMarketRepository
    ): CoinMarketRepository
}


