package net.android.amazonapp.database

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import net.android.amazonapp.viewmodel.PostListViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            val db =
                Room.databaseBuilder(context.applicationContext, AppDataBAse::class.java, "post")
                    .build()
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(db.postDao()) as T
        }
        throw IllegalAccessException("Uknown ViewModel cast")
    }
}