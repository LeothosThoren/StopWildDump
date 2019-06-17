package fr.leothosthoren.stopwilddump.base

import androidx.lifecycle.ViewModel
import fr.leothosthoren.stopwilddump.di.component.DaggerViewModelInjector
import fr.leothosthoren.stopwilddump.di.component.ViewModelInjector
import fr.leothosthoren.stopwilddump.di.module.NetworkModule
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import fr.leothosthoren.stopwilddump.ui.home.HomeViewModel
import fr.leothosthoren.stopwilddump.ui.map.WildDumpMapViewModel

abstract class BaseViewModel : ViewModel() {
    /**
     * Dagger2 build configuration
     * */
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }


    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CommonViewModel -> injector.inject(this)
            is WildDumpMapViewModel -> injector.inject(this)
            is HomeViewModel -> injector.inject(this)
        }
    }
}