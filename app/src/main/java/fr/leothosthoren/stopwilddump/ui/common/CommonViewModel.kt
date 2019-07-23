package fr.leothosthoren.stopwilddump.ui.common

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.base.BaseViewModel
import fr.leothosthoren.stopwilddump.data.models.landfill_models.Landfill
import fr.leothosthoren.stopwilddump.data.models.wildump_models.DumpData
import fr.leothosthoren.stopwilddump.data.remote.WildDumpApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

class CommonViewModel : BaseViewModel() {

    @Inject
    lateinit var wildDumpApi: WildDumpApi

    private lateinit var subscription: Disposable
    // val
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val wildDumpData: MediatorLiveData<DumpData> = MediatorLiveData()
    val landfills: MediatorLiveData<Landfill> = MediatorLiveData()

    init {
        loadWildDumpObject()
        loadJsonFile()
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
                },
                {
                    onError()
                }
            )
    }

    private fun openJsonFile(): String? {
        var json: String? = null
        val file = "res/raw/landfills_list.json"
        try {
            val inputStream = javaClass.classLoader?.getResourceAsStream(file)
            val size = inputStream?.available()
            val buffer = ByteArray(size!!)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }

    private fun loadJsonFile() {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Landfill> = moshi.adapter(Landfill::class.java)
        val result = adapter.fromJson(openJsonFile()!!)
        landfills.value = result
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