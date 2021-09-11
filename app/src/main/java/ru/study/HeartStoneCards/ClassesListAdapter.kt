package ru.study.HeartStoneCards

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.study.HeartStoneCards.models.Card

class ClassesListAdapter(private val itemClickListener: (card: Card) -> Unit) : RecyclerView.Adapter<ClassesListAdapter.ViewHolder>() {
    private var elements: List<Card> = listOf()

    class ViewHolder(view: View, val itemClickListener: (card: Card) -> Unit) : RecyclerView.ViewHolder(view) {

        private val name: TextView = view.findViewById(R.id.name)
        private val type: TextView = view.findViewById(R.id.type)
        private val attackDetails: TextView = view.findViewById(R.id.attackDetails)
        private val durability: TextView = view.findViewById(R.id.durability)
        private val costDetails: TextView = view.findViewById(R.id.costDetails)
        private val attack: TextView = view.findViewById(R.id.attack)
        private val durabilityDetails: TextView = view.findViewById(R.id.durabilityDetails)
        private val cost: TextView = view.findViewById(R.id.cost)


        fun bind(card: Card) {
            name.text = card.name
            type.text = card.type

            if (card.attack != null) {
                attack.visibility = View.VISIBLE
                attackDetails.visibility = View.VISIBLE
                attackDetails.text = card.attack
            } else {
                attack.visibility = View.GONE
                attackDetails.visibility = View.GONE
            }

            if (card.durability != null) {
                durability.visibility = View.VISIBLE
                durabilityDetails.visibility = View.VISIBLE
                durabilityDetails.text = card.durability
            } else {
                durability.visibility = View.GONE
                durabilityDetails.visibility = View.GONE
            }

            if (card.cost != null) {
                cost.visibility = View.VISIBLE
                costDetails.visibility = View.VISIBLE
                costDetails.text = card.cost
            } else {
                cost.visibility = View.GONE
                costDetails.visibility = View.GONE
            }

//            card.attack?.let {
//                attack.visibility = View.VISIBLE
//                attackDetails.visibility = View.VISIBLE
//                attackDetails.text = card.attack
//            }
//            card.cost?.let {
//                cost.visibility = View.VISIBLE
//                costDetails.visibility = View.VISIBLE
//                costDetails.text = card.cost
//            }

            itemView.setOnClickListener {
                itemClickListener(card)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.classes_adapter_element, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(elements[position])
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    fun updateList(elements: List<Card>) {
        this.elements = elements
        notifyDataSetChanged()
    }
}
