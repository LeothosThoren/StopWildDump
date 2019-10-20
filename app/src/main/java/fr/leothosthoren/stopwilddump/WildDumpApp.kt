package fr.leothosthoren.stopwilddump

import android.app.Application
import fr.leothosthoren.stopwilddump.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WildDumpApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WildDumpApp)
            androidLogger()
            modules(appModule)
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