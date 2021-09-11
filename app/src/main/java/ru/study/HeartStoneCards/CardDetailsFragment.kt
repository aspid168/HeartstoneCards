package ru.study.HeartStoneCards

import android.os.Build
import android.os.Bundle
import android.text.Html
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
    private var cardSetDetails: TextView? = null
    private var typeDetails: TextView? = null
    private var textDetails: TextView? = null
    private var factionDetails: TextView? = null
    private var costDetails: TextView? = null
    private var attackDetails: TextView? = null
    private var durabilityDetails: TextView? = null
    private var faction: TextView? = null
    private var cost: TextView? = null
    private var attack: TextView? = null
    private var durability: TextView? = null
    private var image: ImageView? = null
    private var noImage: TextView? = null
    private lateinit var liveData: LiveData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameDetails = view.findViewById(R.id.nameDetails)
        image = view.findViewById(R.id.image)
        noImage = view.findViewById(R.id.noImage)
        cardSetDetails = view.findViewById(R.id.cardSetDetails)
        typeDetails = view.findViewById(R.id.typeDetails)
        textDetails = view.findViewById(R.id.textDetails)
        factionDetails = view.findViewById(R.id.factionDetails)
        costDetails = view.findViewById(R.id.costDetails)
        attackDetails = view.findViewById(R.id.attackDetails)
        durabilityDetails = view.findViewById(R.id.durabilityDetails)
        faction = view.findViewById(R.id.faction)
        cost = view.findViewById(R.id.cost)
        attack = view.findViewById(R.id.attack)
        durability = view.findViewById(R.id.durability)

        liveData = ViewModelProvider(requireActivity()).get(LiveData::class.java)
        liveData.currentCardDetails.observe(requireActivity(), {
            it.img?.let { img ->
                noImage?.visibility = View.GONE
                Picasso.get().load(img).into(image)
            }
            nameDetails?.text = it.name
            cardSetDetails?.text = it.cardSet
            typeDetails?.text = it.type
            it.text?.let { text ->
                textDetails?.visibility = View.VISIBLE
                textDetails?.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    (Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
                } else {
                    Html.fromHtml(text);
                }
            }
            it.faction?.let { faction ->
                this.faction?.visibility = View.VISIBLE
                factionDetails?.visibility = View.VISIBLE
                factionDetails?.text = faction
            }
            it.cost?.let { cost ->
                this.cost?.visibility = View.VISIBLE
                costDetails?.visibility = View.VISIBLE
                costDetails?.text = cost
            }
            it.attack?.let { attack ->
                this.attack?.visibility = View.VISIBLE
                attackDetails?.visibility = View.VISIBLE
                attackDetails?.text = attack

            }
            it.durability?.let { durability ->
                this.durability?.visibility = View.VISIBLE
                durabilityDetails?.visibility = View.VISIBLE
                durabilityDetails?.text = durability
            }
        })
    }
}
