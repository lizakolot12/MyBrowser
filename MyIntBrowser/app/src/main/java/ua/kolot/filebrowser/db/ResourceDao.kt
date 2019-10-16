package ua.kolot.filebrowser.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ua.kolot.filebrowser.data.model.Resource

@Dao
interface ResourceDao {
    @Insert(onConflict = REPLACE)
    fun save(resource: Resource)

    @Query("SELECT * FROM Resource WHERE id = :id LIMIT 1")
    fun load(id: Int): LiveData<Resource>

    @Query("DELETE FROM Resource WHERE id = :id")
    fun deleteResource(id: Int)

    @Query("SELECT * FROM Resource ")
    fun getAllResource(): LiveData<List<Resource>>

    @Query("SELECT * FROM Resource ")
    fun getAll(): List<Resource>

}
