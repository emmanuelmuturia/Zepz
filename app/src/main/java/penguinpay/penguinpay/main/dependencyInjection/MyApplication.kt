package penguinpay.penguinpay.main.dependencyInjection

import android.app.Application
import emmanuelmuturia.ktor.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import penguinpay.penguinpay.home.dependencyInjection.homeKoinModule
import timber.log.Timber

class MyApplication: Application() {

    override fun onCreate() {

        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(tree = Timber.DebugTree())

        startKoin {
            androidContext(androidContext = this@MyApplication)
            modules(modules = listOf(
                homeKoinModule
            ))
        }

    }

}