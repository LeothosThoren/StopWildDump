package fr.leothosthoren.stopwilddump.data.repository

import fr.leothosthoren.stopwilddump.data.models.wilddump.DumpData
import io.reactivex.Flowable

interface WildDumpRepository {
    fun getWildDumpData(): Flowable<DumpData>
}