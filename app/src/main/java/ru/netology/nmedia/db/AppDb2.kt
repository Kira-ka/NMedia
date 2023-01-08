package ru.netology.nmedia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dao.PostDao2
import ru.netology.nmedia.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDb2 : RoomDatabase() {
    abstract fun postDao2(): PostDao2

    companion object {
        @Volatile
        private var instance: AppDb2? = null

        fun getInstance(context: Context): AppDb2 {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb2::class.java, "app.db")
                .allowMainThreadQueries()
                .build()
    }
}
