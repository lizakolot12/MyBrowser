package ua.kolot.filebrowser.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ua.kolot.filebrowser.data.source.ResourceRepository
import ua.kolot.filebrowser.db.ResourceDao
import ua.kolot.filebrowser.db.ResourceDatabase
import ua.kolot.filebrowser.ui.editresource.EditResourceViewModel
import ua.kolot.filebrowser.ui.list.ListResourceViewModel
import javax.inject.Singleton

@Module
public class DataModule {
    private lateinit var mApp: Application

    constructor(app: Application) {
        this.mApp = app
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return mApp
    }

    @Provides
    fun resourceDao(application: Application): ResourceDao {
        return ResourceDatabase.getDatabase(application).resourceDao()
    }

    @Singleton
    @Provides
    fun repository(resourceDao: ResourceDao): ResourceRepository {
        return ResourceRepository(resourceDao)
    }

    @Provides
    fun editViewModel(repository: ResourceRepository): EditResourceViewModel {
        return EditResourceViewModel(repository)
    }


    @Provides
    fun listViewModel(repository: ResourceRepository): ListResourceViewModel {
        return ListResourceViewModel(repository)
    }
}


