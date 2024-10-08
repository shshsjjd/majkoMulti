package com.example.majkomulti.di
import com.example.majkomulti.platform.permission.platformPermissionsModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object KoinInjector {

    val koinApp = startKoin{
        logger(org.koin.core.logger.PrintLogger())
        loadKoinModules(
            listOf(
                networkModule,
                preferencesModule,
                apiModule,
                managerModule,
                repositoryModule,
                platformPermissionsModule
            )
        )

    }

    val koin = koinApp.koin

    fun loadModules(modules: List<Module>){
        koinApp.koin.loadModules(modules)
    }
}