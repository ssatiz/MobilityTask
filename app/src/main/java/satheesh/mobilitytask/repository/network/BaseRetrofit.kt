package satheesh.mobilitytask.repository.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import satheesh.mobilitytask.BuildConfig

open class BaseRetrofit : BaseOkHttp() {

    fun gsonBuilder(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    /**
     * Generate retrofit object with base url along with the required converter factory
     */
    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.base_url)
                .client(provideOKHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}