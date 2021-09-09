package ru.study.HeartStoneCards

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.study.HeartStoneCards.models.Card
import ru.study.HeartStoneCards.models.Categories
import ru.study.HeartStoneCards.models.ListOfCards

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private var cardsCategories: RecyclerView? = null
    private lateinit var adapter: ClassesListAdapter
    private lateinit var liveData: LiveData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        liveData = ViewModelProvider(requireActivity()).get(LiveData::class.java)

        cardsCategories = view.findViewById(R.id.cardsCategories)
        cardsCategories?.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = ClassesListAdapter {
            val act = requireActivity()
            if (act is MainActivityNavigator) {
                liveData.setCurrentCardDetails(it)
                Log.v("fr1", liveData.currentCardDetails.value.toString())
                act.goToCardDetailsFragment()
            }
        }
        cardsCategories?.adapter = adapter
        liveData.classData.observe(requireActivity(), {
            (adapter).updateList(it)
        })
    }
}
