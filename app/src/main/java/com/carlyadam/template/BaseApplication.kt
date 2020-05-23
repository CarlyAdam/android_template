package com.carlyadam.template

import android.app.Application
import com.carlyadam.template.di.AppModule.albumModule
import com.carlyadam.template.di.AppModule.webServiceModule
import com.carlyadam.template.utilities.Constant.AUTHOR
import org.acra.ACRA
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


@ReportsCrashes(
    mailTo = AUTHOR,
    mode = ReportingInteractionMode.TOAST,
    resToastText = R.string.crash_text
)
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val appComponent = listOf(webServiceModule, albumModule)
        startKoin {
            androidContext(this@BaseApplication)
            modules(appComponent)
        }
        ACRA.init(this)
    }


}