package ao.com.dio.portfoliogithub.ui

import android.app.Application
import ao.com.dio.portfoliogithub.data.di.DataModule
import ao.com.dio.portfoliogithub.domain.di.DomainModule
import ao.com.dio.portfoliogithub.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
        }
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}