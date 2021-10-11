package ru.study.HeartStoneCards.data.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.study.HeartStoneCards.RetrofitAndGsonInstances
import ru.study.HeartStoneCards.domain.repository.CardsRepository
import ru.study.HeartStoneCards.domain.models.Card
import ru.study.HeartStoneCards.domain.models.Categories
import java.util.*

class CardsRepositoryImpl: CardsRepository {
    override fun getClasses(changeClassesData: (categories: Categories) -> Unit) {
        val single: Disposable = RetrofitAndGsonInstances.retrofit.getAllCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            changeClassesData(it)
                        }, {

                }
                )
    }

    override fun getCards(cardName: String, changeCardsData: (listOfCards: List<Card>) -> Unit): Disposable {
        return RetrofitAndGsonInstances.retrofit
                .getCardsByClass(cardName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            changeCardsData(it)
                        }, {

                })
    }

    override fun searchCard(searchText: String, classData: MutableLiveData<List<Card>>) {
        val list = mutableListOf<Card>()
        classData.value?.forEach {
            if (searchText.length <= it.name.length) {
                val firstPartOfCardName = it.name.removeRange(searchText.length, it.name.length)
                        .toLowerCase(Locale.ROOT)
                if (firstPartOfCardName == searchText.toLowerCase(Locale.ROOT)) {
                    list.add(it)
                }
            }
        }
        classData.value = list
    }
}
