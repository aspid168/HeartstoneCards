package ru.study.HeartStoneCards

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.annotations.NotNull
import ru.study.HeartStoneCards.models.Card
import ru.study.HeartStoneCards.models.Categories
import ru.study.HeartStoneCards.models.ListOfCards
import ru.study.HeartStoneCards.repository.Repository

class LiveData: ViewModel() {

    var repository: Repository = Model()

    var classes: MutableLiveData<Categories> = MutableLiveData<Categories>()

    val classData: MutableLiveData<List<Card>> = MutableLiveData<List<Card>>()

    val currentCardDetails: MutableLiveData<Card> = MutableLiveData<Card>()

    init {
        getClasses()
    }

    private fun getClasses() {
        repository.getClasses(classes)
    }

    fun getCards(currentClass: String) {
        repository.getCards(currentClass, classData)
    }

    fun setCurrentCardDetails(card: Card) {
        currentCardDetails.value = card
    }

    fun clearDisposeBag() {
        repository.cancelTasks()
    }

}
