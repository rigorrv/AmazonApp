package net.android.amazonapp.injection

import dagger.Component
import net.android.amazonapp.viewmodel.PostListViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjection {

    fun injection(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder{
        fun build (): ViewModelInjection
        fun networkModule(networkModule : NetworkModule):Builder
    }
}