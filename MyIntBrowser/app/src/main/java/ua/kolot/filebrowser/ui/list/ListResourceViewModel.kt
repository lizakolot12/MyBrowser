package ua.kolot.filebrowser.ui.list

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.kolot.filebrowser.data.model.Resource
import ua.kolot.filebrowser.data.source.ResourceRepository
import javax.inject.Inject

class ListResourceViewModel @Inject constructor(val repository: ResourceRepository) : ViewModel() {
        private var resources: MediatorLiveData<List<Resource>> = MediatorLiveData()
        private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

        init {
            viewModelScope.launch(Dispatchers.IO) {
                resources.addSource(repository.getAllResources(), { t -> resources.postValue(t) })
            }
        }

        fun getAll() = resources
    }
