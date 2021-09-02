package ru.study.HeartStoneCards.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import ru.study.HeartStoneCards.models.SongName

interface ServerRetrofit {

    @GET("cards")
    @Headers("x-rapidapi-host:the-cocktail-db.p.rapidapi.com", "x-rapidapi-key:ed4b2e2176msh8349a6249b0d160p1b893djsn4e250e355e5e")
    fun getAllCards(): Single<SongName> // get all info -> search by this info
}
