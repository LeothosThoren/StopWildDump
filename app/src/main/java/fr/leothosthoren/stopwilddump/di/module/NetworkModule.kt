package fr.leothosthoren.stopwilddump.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import fr.leothosthoren.stopwilddump.data.remote.WildDumpApi
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://albanbernard.fr/private/"

@Module
object NetworkModule {
    /**
     * Provides the Api service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Api service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideWildDumpApi(retrofit: Retrofit): WildDumpApi {
        return retrofit.create(WildDumpApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}