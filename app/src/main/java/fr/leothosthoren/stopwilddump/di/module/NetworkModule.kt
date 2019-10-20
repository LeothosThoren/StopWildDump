package fr.leothosthoren.stopwilddump.di.module

import fr.leothosthoren.stopwilddump.data.remote.WildDumpApi
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://albanbernard.fr/private/"

object NetworkModule {

    fun wildDumpService(): WildDumpApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build().create(WildDumpApi::class.java)
    }
}