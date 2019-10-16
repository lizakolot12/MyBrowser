package ua.kolot.filebrowser.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.kolot.filebrowser.data.model.Resource


@Database(entities = [Resource::class], version = 1)
abstract class ResourceDatabase : RoomDatabase() {
    abstract fun resourceDao(): ResourceDao

    companion object {
        @Volatile
        private var INSTANCE: ResourceDatabase? = null

        fun getDatabase(context: Context): ResourceDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResourceDatabase::class.java,
                    "resource_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}