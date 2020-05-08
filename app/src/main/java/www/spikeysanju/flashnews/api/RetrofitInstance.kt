package www.spikeysanju.flashnews.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.spikeysanju.flashnews.utils.Constants.Companion.URL
import kotlin.math.log

class RetrofitInstance {

companion object {

    private val  retrofit by lazy {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api by lazy {

        retrofit.create(NewsApi::class.java)
    }

}

}