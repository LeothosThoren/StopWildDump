package fr.leothosthoren.stopwilddump.di.component

import dagger.Component
import fr.leothosthoren.stopwilddump.di.module.NetworkModule
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import fr.leothosthoren.stopwilddump.ui.home.HomeViewModel
import fr.leothosthoren.stopwilddump.ui.map.WildDumpMapViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters / controllers.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified ViewModel.
     * @param CommonViewModel commonViewModel in which to inject the dependencies
     */
    fun inject(commonViewModel: CommonViewModel)

    /**
     * Injects required dependencies into the specified ViewModel.
     * @param HomeViewModel homeViewModel in which to inject the dependencies
     */
    fun inject(homeViewModel: HomeViewModel)


    /**
     * Injects required dependencies into the specified ViewModel.
     * @param WildDumpMapViewModel mapViewModel in which to inject the dependencies
     */
    fun inject(mapViewModel: WildDumpMapViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}