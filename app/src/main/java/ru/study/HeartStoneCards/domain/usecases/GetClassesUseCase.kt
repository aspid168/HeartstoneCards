package ru.study.HeartStoneCards.domain.usecases

import ru.study.HeartStoneCards.domain.repository.CardsRepository
import ru.study.HeartStoneCards.domain.models.Categories

class GetClassesUseCase(private val cardsRepository: CardsRepository) {
    fun execute(changeClassesData: (categories: Categories) -> Unit) {
        cardsRepository.getClasses(changeClassesData)
    }
}
