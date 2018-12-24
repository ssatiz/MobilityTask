package satheesh.mobilitytask.repository.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

open class BaseOkHttp {


    fun provideOKHttpClient(): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()

        okHttpClient
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)

         okHttpClient.addInterceptor(provideHttpLoggingInterceptor())

        return okHttpClient.build()
    }

//    private fun provideBasicAuthInterceptor(): Interceptor {
//        val credentials = Credentials.basic(BuildConfig.username, BuildConfig.password)
//        Log.d("credentials", "credentials: $credentials")
//        return Interceptor { chain ->
//            val request = chain.request()
//            val authenticatedRequest = request.newBuilder()
//                    .header("Authorization", credentials).build()
//            chain.proceed(authenticatedRequest)
//        }
//    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

}