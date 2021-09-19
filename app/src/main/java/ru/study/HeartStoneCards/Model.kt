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
import kotlin.math.sin

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
    }

    private var getCards: Disposable? = null
    override fun getCards(cardName: String, classData: MutableLiveData<List<Card>>) {

        getCards= RetrofitAndGsonInstances.retrofit.getCardsByClass(cardName)
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        {
                            classData.postValue(it)
                        }, {
//                    Log.v("error", it.localizedMessage.toString())
                }
                )
        disposeBag.add(getCards)
    }

    override fun checkDisposeBag() {
        if(disposeBag.size() > 1 && getCards != null) {
            clearDisposeBag()
        }
    }

    override fun clearDisposeBag() {
        disposeBag.remove(getCards)
        disposeBag.clear()
    }
}
