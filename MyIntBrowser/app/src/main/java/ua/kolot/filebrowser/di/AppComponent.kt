package ua.kolot.filebrowser.di

import dagger.Component
import ua.kolot.filebrowser.ui.editresource.EditResourceViewModel
import ua.kolot.filebrowser.ui.list.ListResourceViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
public interface AppComponent {
    fun editResourceViewModel(): EditResourceViewModel
    fun listResourceViewModel(): ListResourceViewModel
}