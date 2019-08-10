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

    // TODO: gérer la navigation et adapter le bottombar et la toolbar à mon besoin,
    //  déplacer la logique de connexion dans le bon viewmodel et gérer les erreurs en cas de hors connexion,
    //  ajuster les vues selon l'approche android,
    //  gérer le problème avec les popup de la carte google
    //  créer la liste et compléter avec la pagination,
    //  faire les écrans d'infos et imaginer la tête de l'écran de profil
    //  faire le deuxième écran avec le geocoding,
    //  ajouter un repository plutôt que de dépendre du viewModel,
    //  Bonus imaginer une approche avec Koin,
    //  bonus ajouter de la persistence avec Room,
    //  bonus ajouter un système de creation de compte et authentification via sharedpreferences


}