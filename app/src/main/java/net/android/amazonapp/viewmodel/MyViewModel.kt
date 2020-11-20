package net.android.amazonapp.viewmodel

import androidx.lifecycle.ViewModel
import net.android.amazonapp.injection.DaggerViewModelInjection
import net.android.amazonapp.injection.NetworkModule
import net.android.amazonapp.injection.ViewModelInjection

open class MyViewModel : ViewModel(){

    private val injector : ViewModelInjection= DaggerViewModelInjection
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when(this){
            is PostListViewModel-> injector.injection(this)
        }
    }
}