package fr.leothosthoren.stopwilddump.ui.wildumplist

import androidx.paging.ItemKeyedDataSource
import fr.leothosthoren.stopwilddump.data.models.wilddump.WildDumpsItem
import fr.leothosthoren.stopwilddump.data.remote.WildDumpApi

class WildDumpDataSource(api: WildDumpApi) : ItemKeyedDataSource<Int, WildDumpsItem>() {
    override fun getKey(item: WildDumpsItem): Int {
        return item.id!!
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<WildDumpsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<WildDumpsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<WildDumpsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}