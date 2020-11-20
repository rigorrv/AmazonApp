package net.android.amazonapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import net.android.amazonapp.database.PostDao
import net.android.amazonapp.model.JsonData
import net.android.amazonapp.model.JsonDataItem
import net.android.amazonapp.networking.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao) : MyViewModel() {

    companion object {
        var sortById = "ID"
    }

    @Inject
    lateinit var api: API
    var jsonData = MutableLiveData<List<JsonDataItem>>()
    var noData = MutableLiveData<Boolean>()

    init {
        this.checkDB()
    }

    @Throws(IllegalAccessException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 nonstopcode.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }

    fun checkDB() {
        viewModelScope.launch(Dispatchers.IO + NonCancellable) {
            if (postDao.byListId.size > 1) {
                loadSql()
            } else {
                getJson()
            }
        }
    }


    private fun getJson() {
        if (isConnected()) {
            noData.postValue(false)
            viewModelScope.launch(Dispatchers.IO + NonCancellable) {
                api.getJson().enqueue(object : Callback<JsonData> {
                    override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                        //saveJson(response.body()!!)
                        saveDataBase(response.body()!!)
                    }

                    override fun onFailure(call: Call<JsonData>, t: Throwable) {
                    }
                })
            }
        } else {
            noData.postValue(true)
        }
    }


    fun saveDataBase(items: JsonData) {
        for (result in items) {
            var stars = 0
            var price = 0
            val name = result.name?.replace("%20", "")
            val imageUrl = "http://nonstopcode.com/rigo/amazon/img/$name.jpg"
            for (random in 0..300) {
                stars = (0..4).random()
                price = (9..30).random()
            }
            viewModelScope.launch(Dispatchers.IO + NonCancellable) {
                postDao.insertInfo(
                    JsonDataItem(
                        result.id,
                        result.listId,
                        result.name,
                        stars,
                        price,
                        imageUrl
                    )
                )
                loadSql()
            }
        }
    }

    fun loadSql() {
        if (sortById.contains("ID")) {
            viewModelScope.launch(Dispatchers.IO + NonCancellable) {
                jsonData.postValue(postDao.byListId)
            }
        }
        if (sortById.contains("Name")) {
            viewModelScope.launch(Dispatchers.IO + NonCancellable) {
                jsonData.postValue(postDao.byName)
            }
        }

    }
}
