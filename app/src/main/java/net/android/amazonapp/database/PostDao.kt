package net.android.amazonapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.android.amazonapp.model.JsonDataItem

@Dao
interface PostDao {

    @get:Query("SELECT * FROM info_table WHERE name!='' AND name IS NOT NULL ORDER BY CAST(name AS int)")
    val byName : List<JsonDataItem>

    @get:Query("SELECT * FROM info_table WHERE name!='' AND name IS NOT NULL ORDER BY CAST(listId AS int)")
    val byListId : List<JsonDataItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfo (vararg jsonData: JsonDataItem)
}