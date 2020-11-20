package net.android.amazonapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {


    @TypeConverter
    fun fromAddressList(addressLang : List<JsonDataItem?>?): String?{
        val type = object : TypeToken<List<JsonDataItem?>?>(){}.type
        return Gson().toJson(addressLang,type)
    }
    @TypeConverter
    fun toAddresList(stringLang : String?): List<JsonDataItem>?{
        val type = object : TypeToken<List<JsonDataItem?>?>(){}.type
        return Gson().fromJson<List<JsonDataItem>>(stringLang,type)
    }

    @TypeConverter
    fun stringListToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

}