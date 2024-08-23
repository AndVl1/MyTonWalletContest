package ru.andvl.mytonwallet.contest

import android.app.Application
//import com.vk.recompose.logger.RecomposeLoggerConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.andvl.mytonwallet.contest.di.appModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        RecomposeLoggerConfig.isEnabled = true

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}
