package fr.leothosthoren.stopwilddump.di.module

import fr.leothosthoren.stopwilddump.data.repository.WildDumpRepository
import fr.leothosthoren.stopwilddump.data.repository.WildDumpRepositoryImpl
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<WildDumpRepository> { WildDumpRepositoryImpl() }
    viewModel { CommonViewModel(get()) }
}