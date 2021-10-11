package ru.study.HeartStoneCards.domain.usecases

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.study.HeartStoneCards.domain.repository.CardsRepository
import ru.study.HeartStoneCards.domain.models.Card

class GetCardsByClassUseCase(private val cardsRepository: CardsRepository) {
    private var getCards: Disposable? = null
    private val disposeBag = CompositeDisposable()
    fun execute(cardName: String, changeCardsData: (listOfCards: List<Card>)-> Unit) {
        checkDisposeBag()
        getCards = cardsRepository.getCards(cardName, changeCardsData)
        disposeBag.add(getCards)
    }

    private fun checkDisposeBag() {
        if(disposeBag.size() > 1 && getCards != null) {
            clearDisposeBag()
        }
    }

    private fun clearDisposeBag() {
        disposeBag.remove(getCards)
        disposeBag.clear()
    }
}
