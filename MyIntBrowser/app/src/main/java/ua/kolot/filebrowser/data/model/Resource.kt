package ua.kolot.filebrowser.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Resource(
    @PrimaryKey (autoGenerate = true)
    var id: Int ,
    var name: String,
    var description: String,
    var path: String
)