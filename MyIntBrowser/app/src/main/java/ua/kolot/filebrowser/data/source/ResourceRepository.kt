package ua.kolot.filebrowser.data.source

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ua.kolot.filebrowser.data.model.Resource
import ua.kolot.filebrowser.db.ResourceDao

class ResourceRepository(private var resourceDao: ResourceDao) {

    @WorkerThread
    suspend fun saveResource(resource: Resource) {
        resourceDao.save(resource)
    }

    @WorkerThread
    suspend fun getAllResources(): LiveData<List<Resource>> {
        return resourceDao.getAllResource()
    }

    @WorkerThread
    suspend fun getById(id:Int): LiveData<Resource> {
        return resourceDao.load(id)
    }

    fun getAll(): List<Resource> {
        return resourceDao.getAll()
    }
}