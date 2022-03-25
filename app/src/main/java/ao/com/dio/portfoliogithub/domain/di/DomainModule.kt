package ao.com.dio.portfoliogithub.domain.di

import ao.com.dio.portfoliogithub.data.di.DataModule
import ao.com.dio.portfoliogithub.domain.ListUserRepositoryUserCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    fun load() {
        loadKoinModules(useCaseModule())
    }
    private fun useCaseModule():Module{
        return module {
            factory {
                ListUserRepositoryUserCase(get())
            }
        }
    }
}