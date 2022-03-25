package ao.com.dio.portfoliogithub.presentation.di

import ao.com.dio.portfoliogithub.domain.ListUserRepositoryUserCase
import ao.com.dio.portfoliogithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    fun load() {
        loadKoinModules(viewModelModule())
    }
    private fun viewModelModule(): Module {
        return module {
            viewModel { MainViewModel(get()) }
        }
    }
}