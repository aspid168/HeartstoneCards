package ru.study.HeartStoneCards.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.study.HeartStoneCards.data.repository.CardsRepositoryImpl
import ru.study.HeartStoneCards.domain.models.Card
import ru.study.HeartStoneCards.domain.models.Categories
import ru.study.HeartStoneCards.domain.usecases.GetCardsByClassUseCase
import ru.study.HeartStoneCards.domain.usecases.GetClassesUseCase

class MainViewModel: ViewModel() {

    private val repository: CardsRepositoryImpl = CardsRepositoryImpl()
    private val getClassesUseCase: GetClassesUseCase = GetClassesUseCase(repository)
    private val getCardsByClassUseCase: GetCardsByClassUseCase = GetCardsByClassUseCase(repository)
//    var repository: CardsRepository = CardsRepositoryImpl()

    val classes: MutableLiveData<Categories> = MutableLiveData<Categories>()

    var classData: MutableLiveData<List<Card>> = MutableLiveData<List<Card>>()

    val currentCardDetails: MutableLiveData<Card> = MutableLiveData<Card>()

    init {
        getClasses()
    }

    private fun getClasses() {
        getClassesUseCase.execute {
            classes.value = it
        }
    }

    fun getCards(currentClass: String) {
        getCardsByClassUseCase.execute(currentClass) {
            classData.value = it
        }

    }

    fun setCurrentCardDetails(card: Card) {
        currentCardDetails.value = card
    }

//    fun checkDisposeBag() {
//        repository.checkDisposeBag()
//    }

    //

    var classSearch: MutableLiveData<List<Card>> = MutableLiveData<List<Card>>()

    fun changeClassData(searchText: String) {
        classSearch.value = classData.value
        repository.searchCard(searchText, classData)
    }

    fun returnClassData() {
        classData.value = classSearch.value
    }

}
