package fr.leothosthoren.stopwilddump.data.remote

import fr.leothosthoren.stopwilddump.data.wildump_models.DumpData
import io.reactivex.Flowable
import retrofit2.http.GET


interface WildDumpApi {

    @GET("decharges.json")
    fun getWildDumpObject(): Flowable<DumpData>
}