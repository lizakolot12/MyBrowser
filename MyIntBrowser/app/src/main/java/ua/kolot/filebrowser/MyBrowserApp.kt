package ua.kolot.filebrowser

import android.app.Application
import ua.kolot.filebrowser.di.AppComponent
import ua.kolot.filebrowser.di.DaggerAppComponent
import ua.kolot.filebrowser.di.DataModule

class MyBrowserApp : Application() {
    lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .build()
    }
}
