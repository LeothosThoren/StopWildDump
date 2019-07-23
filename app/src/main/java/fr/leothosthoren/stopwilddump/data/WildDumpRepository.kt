package fr.leothosthoren.stopwilddump.data

import android.util.Log
import fr.leothosthoren.stopwilddump.data.models.wildump_models.DumpData
import fr.leothosthoren.stopwilddump.data.remote.WildDumpApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WildDumpRepository {

    @Inject
    lateinit var wildDumpApi: WildDumpApi

    private fun loadWildDumpObject(): Disposable {
        return wildDumpApi.getWildDumpObject()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .doOnSubscribe { onSubscribe() }
             .subscribe(
                 // Add result
                 { result ->
                     onSuccess(result)
                     Log.d("DEBUG", "${result.informations}")

                 },

                 {
                     onError()
                     Log.d("DEBUG", "${it.message}")
                 }
             )
    }

    private fun onSubscribe() {

    }

    private fun onError() {

    }

    private fun onSuccess(wildDumpData: DumpData?) {

    }
}