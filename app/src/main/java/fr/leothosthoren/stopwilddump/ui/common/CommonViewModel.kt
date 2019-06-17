package fr.leothosthoren.stopwilddump.ui.common

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.base.BaseViewModel
import fr.leothosthoren.stopwilddump.data.remote.WildDumpApi
import fr.leothosthoren.stopwilddump.data.wildump_models.DumpData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommonViewModel : BaseViewModel() {

    @Inject
    lateinit var wildDumpApi: WildDumpApi

    private lateinit var subscription: Disposable
    // val
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val wildDumpData: MediatorLiveData<DumpData> = MediatorLiveData()


    init {
        loadWildDumpObject()
    }

    private fun loadWildDumpObject() {
        subscription = wildDumpApi.getWildDumpObject()
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
        errorMessage.value = null
    }

    private fun onError() {
        errorMessage.value = R.string.error_message
    }

    private fun onSuccess(wildDumpData: DumpData?) {
        this.wildDumpData.value = wildDumpData
    }


    /**
     * To dispose subscription when the viewModel is no longer used and will be destroyed
     */
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}