package ru.study.HeartStoneCards

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.study.HeartStoneCards.models.Card
import ru.study.HeartStoneCards.models.Categories
import ru.study.HeartStoneCards.repository.Repository
import kotlin.math.sin

class Model : Repository {

    override fun getClasses(classes: MutableLiveData<Categories>) {
        val single: Single<Categories> = RetrofitAndGsonInstances.retrofit.getAllCategories()
        single.subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            classes.postValue(it)
                        }, {
                    Log.v("error", it.localizedMessage.toString())
                }
                )

    }

    override fun getCards(cardName: String, classData: MutableLiveData<List<Card>>) {
        val single: Single<List<Card>> = RetrofitAndGsonInstances.retrofit.getCardsByClass(cardName)
//        val result: Disposable  = Single.just(RetrofitAndGsonInstances.retrofit.getCardsByClass(cardName)).subscribeOn(Schedulers.io()).subscribe({}, {})
        
        single.subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            classData.postValue(it)
                        }, {
                    Log.v("error", it.localizedMessage.toString())
                }
                )
    }
}
