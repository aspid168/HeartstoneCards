package ru.study.HeartStoneCards

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import ru.study.HeartStoneCards.models.Card
import ru.study.HeartStoneCards.models.Categories
import ru.study.HeartStoneCards.repository.Repository

class Model : Repository {

    private val disposeBag = CompositeDisposable()

    override fun getClasses(classes: MutableLiveData<Categories>) {
        val single: Disposable = RetrofitAndGsonInstances.retrofit.getAllCategories()
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        {
                            classes.postValue(it)
                        }, {
//                    Log.v("error", it.localizedMessage.toString())
                }
                )
        disposeBag.add(single)
    }

    override fun getCards(cardName: String, classData: MutableLiveData<List<Card>>) {

        val single: Disposable = RetrofitAndGsonInstances.retrofit.getCardsByClass(cardName)
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        {
                            classData.postValue(it)
                        }, {
//                    Log.v("error", it.localizedMessage.toString())
                }
                )
        disposeBag.add(single)
    }

    override fun cancelTasks() {
//        if (!disposeBag.isDisposed) {
//            disposeBag.dispose()
            disposeBag.clear()
        Log.v("cancel", "task_cancel")
//        }
    }
}
