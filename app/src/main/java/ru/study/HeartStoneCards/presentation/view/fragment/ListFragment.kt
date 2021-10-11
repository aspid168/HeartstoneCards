package ru.study.HeartStoneCards.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.study.HeartStoneCards.R
import ru.study.HeartStoneCards.presentation.view.adapter.ClassesListAdapter
import ru.study.HeartStoneCards.presentation.MainActivityNavigator
import ru.study.HeartStoneCards.presentation.viewModel.MainViewModel

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private var cardsCategories: RecyclerView? = null
    private lateinit var adapter: ClassesListAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        cardsCategories = view.findViewById(R.id.cardsCategories)
        cardsCategories?.layoutManager = LinearLayoutManager(requireContext())
        adapter = ClassesListAdapter {
            val act = requireActivity()
            if (act is MainActivityNavigator) {
                mainViewModel.setCurrentCardDetails(it)
                act.goToCardDetailsFragment()
            }
        }
        cardsCategories?.adapter = adapter
        mainViewModel.classData.observe(requireActivity(), {
            (adapter).updateList(it)
        })
    }
}
