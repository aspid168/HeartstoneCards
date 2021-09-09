package ru.study.HeartStoneCards

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class App: Application() {

    override fun onCreate() {
        super.onCreate()
//        val liveData = LiveData()
//        val liveData = ViewModelProvider(this as ViewModelStoreOwner).get(LiveData::class.java)
//        liveData.getClasses()
    }

//    private val model = Model()

//    fun getModelInstance(): Model {
//        return model
//    }

//    fun getViewModelInstance(): LiveData {
//        return viewModel
//    }
}
