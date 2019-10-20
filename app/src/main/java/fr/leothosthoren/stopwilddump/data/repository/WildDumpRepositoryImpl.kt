package fr.leothosthoren.stopwilddump.data.repository

import fr.leothosthoren.stopwilddump.data.models.wilddump.DumpData
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

class WildDumpRepositoryImpl : WildDumpRepository {

    override fun getWildDumpData(): Flowable<DumpData> {
        val dis: Disposable

        return dis
    }
}