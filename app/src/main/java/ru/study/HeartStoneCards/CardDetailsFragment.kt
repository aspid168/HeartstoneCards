package ru.study.HeartStoneCards

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.study.HeartStoneCards.models.Card

class CardDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }

    private var nameDetails: TextView? = null
    private var image: ImageView? = null
    private lateinit var liveData: LiveData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameDetails = view.findViewById(R.id.nameDetails)
        image = view.findViewById(R.id.image)

        liveData = ViewModelProvider(requireActivity()).get(LiveData::class.java)
        liveData.currentCardDetails.observe(requireActivity(), {
            nameDetails?.text = it.name
            it.img?.let { img ->
                Picasso.get().load(img).into(image)
            }
        })
    }
}
