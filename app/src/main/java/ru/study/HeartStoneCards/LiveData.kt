package ru.study.HeartStoneCards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveData: ViewModel() {

    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}
