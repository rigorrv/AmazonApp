package net.android.amazonapp.injection

import dagger.Module
import dagger.Provides
import dagger.Reusable
import net.android.amazonapp.networking.API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun apiProvides(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun retrofitProvides():Retrofit{
        val okHttpClient = OkHttpClient()
        return Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}