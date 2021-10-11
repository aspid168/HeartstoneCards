package ru.study.HeartStoneCards.domain.repository

import io.reactivex.Single
import retrofit2.http.*
import ru.study.HeartStoneCards.domain.models.Card
import ru.study.HeartStoneCards.domain.models.Categories

interface ServerRetrofit {

    @GET("info")
    @Headers("x-rapidapi-host:omgvamp-hearthstone-v1.p.rapidapi.com", "x-rapidapi-key:6059a4e7bdmsh5b78dc6bd691948p1dbb5ajsn778bb9a0d70e")
    fun getAllCategories(): Single<Categories>

    @GET("cards/search/{text}")
    @Headers("x-rapidapi-host:omgvamp-hearthstone-v1.p.rapidapi.com", "x-rapidapi-key:6059a4e7bdmsh5b78dc6bd691948p1dbb5ajsn778bb9a0d70e")
    fun searchCards(@Path("text") text: String): Single<List<Card>>

    @GET("cards/classes/{text}")
    @Headers("x-rapidapi-host:omgvamp-hearthstone-v1.p.rapidapi.com", "x-rapidapi-key:6059a4e7bdmsh5b78dc6bd691948p1dbb5ajsn778bb9a0d70e")
    fun getCardsByClass(@Path("text") text: String): Single<List<Card>>
}
