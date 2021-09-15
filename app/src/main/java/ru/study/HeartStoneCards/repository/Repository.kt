package ru.study.HeartStoneCards.repository

import androidx.lifecycle.MutableLiveData
import ru.study.HeartStoneCards.models.Card
import ru.study.HeartStoneCards.models.Categories

interface Repository {
    fun getClasses(classes: MutableLiveData<Categories>)
    fun getCards(cardName: String, classData: MutableLiveData<List<Card>>)
    fun cancelTasks()
}
