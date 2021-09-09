package ru.study.HeartStoneCards.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Card(
    val name: String,
    val cardSet: String,
    val type: String,
    val playerClass: String,
    val text: String?,
    val img: String?,
    val faction: String?,
    val cost: String?,
    val attack: String?,
    val durability: String?
)
