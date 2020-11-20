package net.android.amazonapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.android.amazonapp.model.Converter
import net.android.amazonapp.model.JsonDataItem


@Database(entities = [JsonDataItem::class],version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBAse : RoomDatabase() {
    abstract fun postDao():PostDao
}