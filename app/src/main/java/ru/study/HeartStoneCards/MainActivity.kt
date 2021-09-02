 package ru.study.HeartStoneCards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.study.HeartStoneCards.models.SongName

 class MainActivity : AppCompatActivity() {

    private var search: EditText? = null
    private var searchButton: Button? = null
    private var songs: TextView? = null

    private val model: LiveData = LiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search = findViewById(R.id.search)
        searchButton = findViewById(R.id.searchButton)
        songs = findViewById(R.id.songs)

        val observer = Observer<String> {
            songs?.text = it
        }
        model.currentName.observe(this, observer)


//        searchButton?.setOnClickListener {
//            val observable: Observable<String> = dateSource()
//            observable.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            {
//                                model.currentName.value = it.toString()
//                                Log.v("ok", "ok")
//                            }, {
//                        Log.v("error", it.localizedMessage.toString())
//                    }, {
//                        Log.v("finale", "finale")
//                    }
//                    )
//        }

//        if (savedInstanceState != null) {
//            songs?.text = savedInstanceState.getString("qwe")
//        }
//
        searchButton?.setOnClickListener {
            songs?.text = ""
            val single: Single<SongName> = RetrofitAndGsonInstances.retrofit.getAllCards()
            single.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                    {
                        it.response.hits.forEach {
                            songs?.append(it.result.full_title)
                            songs?.append("\n")
                        }
                    }, {
                        Log.v("error", it.localizedMessage.toString())
                    }
                )
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("qwe", songs?.text.toString())
//    }

     private fun dateSource(): Observable<String> {
         var i = 0
         return Observable.create {
             while (i < 1000) {
                 i++
                 it.onNext(i.toString())
                 Thread.sleep(1000)
             }
         }
     }

    override fun onDestroy() {
        super.onDestroy()
        search = null
        searchButton?.setOnClickListener(null)
        searchButton = null
        songs = null
    }
}
