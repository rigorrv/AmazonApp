package net.android.amazonapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonInclude

@Entity(tableName = "info_table")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
class JsonDataItem(
    @PrimaryKey
    val id: Int,
    val listId: Int,
    val name: String?,
    var stars : Int,
    var price : Int,
    var image : String?
)