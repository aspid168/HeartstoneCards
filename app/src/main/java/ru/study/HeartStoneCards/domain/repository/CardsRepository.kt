package ru.study.HeartStoneCards.domain.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import ru.study.HeartStoneCards.domain.models.Card
import ru.study.HeartStoneCards.domain.models.Categories

interface CardsRepository {
    fun getClasses(changeClassesData: (categories: Categories) -> Unit)
    fun getCards(cardName: String, changeCardsData: (listOfCards: List<Card>) -> Unit): Disposable
    fun searchCard(searchText: String, classData: MutableLiveData<List<Card>>)
}
