package ru.study.HeartStoneCards

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.study.HeartStoneCards.domain.repository.ServerRetrofit

object RetrofitAndGsonInstances {
    val retrofit: ServerRetrofit
    val gson: Gson

    private const val URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/"

    init  {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val rxAdapter = RxJava2CallAdapterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(rxAdapter)
            .build()

        RetrofitAndGsonInstances.retrofit = retrofit.create(ServerRetrofit::class.java)
        gson = Gson()
    }
}
