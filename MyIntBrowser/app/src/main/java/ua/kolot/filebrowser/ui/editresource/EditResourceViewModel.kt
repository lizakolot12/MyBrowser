package ua.kolot.filebrowser.ui.editresource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.kolot.filebrowser.SingleLiveEvent
import ua.kolot.filebrowser.data.model.Resource
import ua.kolot.filebrowser.data.source.ResourceRepository
import javax.inject.Inject

class EditResourceViewModel @Inject constructor(
    public var repository: ResourceRepository
) : ViewModel() {
    private var resources: MediatorLiveData<List<Resource>> = MediatorLiveData()
    private var saveSuccessEvent:SingleLiveEvent<Boolean> = SingleLiveEvent<Boolean>();
    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            resources.addSource(
                repository.getAllResources(),
                Observer { t -> resources.postValue(t) })
        }
    }

    fun save(resource: Resource) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveResource(resource)
            saveSuccessEvent.postValue(true)
        }
    }

    fun getAll() = resources

    fun getById(id: Int): LiveData<Resource>? {
        val result = MediatorLiveData<Resource>()
        viewModelScope.launch(Dispatchers.Default) {
            val resource = repository.getById(id)
            result.addSource(resource, Observer {
                result.postValue(it)
            })
        }
        return result
    }

    fun saveSuccessEvent():LiveData<Boolean> {
        return saveSuccessEvent
    }
}
