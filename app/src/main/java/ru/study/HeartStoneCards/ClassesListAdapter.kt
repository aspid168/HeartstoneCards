package ru.study.HeartStoneCards

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.study.HeartStoneCards.models.Card

class ClassesListAdapter(private val itemClickListener: (card: Card) -> Unit) : RecyclerView.Adapter<ClassesListAdapter.ViewHolder>() {
    private var elements: List<Card> = listOf()

    class ViewHolder(view: View, val itemClickListener: (card: Card) -> Unit) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.name)

        fun bind(card: Card) {
            name.text = card.name
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
