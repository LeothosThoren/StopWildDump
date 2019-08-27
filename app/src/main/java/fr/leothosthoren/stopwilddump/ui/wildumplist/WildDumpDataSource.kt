package fr.leothosthoren.stopwilddump.ui.wildumplist

import androidx.paging.PageKeyedDataSource
import fr.leothosthoren.stopwilddump.data.models.wilddump.WildDumpsItem
import fr.leothosthoren.stopwilddump.data.remote.WildDumpApi

class WildDumpDataSource(api: WildDumpApi) : PageKeyedDataSource<Int, WildDumpsItem>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, WildDumpsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, WildDumpsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, WildDumpsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}