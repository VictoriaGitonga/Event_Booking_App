package com.vicky.project2.ui.dashboard_user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicky.project2.R

class EventAdapter(private val eventList:ArrayList<EventItem>)
    :RecyclerView.Adapter<EventAdapter.EventViewHolder>(){

    var onItemClick: ((EventItem) -> Unit)? = null

    inner class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val textView3: TextView = itemView.findViewById(R.id.textView3)
        val textView4: TextView = itemView.findViewById(R.id.textView4)
        val textView5: TextView = itemView.findViewById(R.id.textView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        //inflate the layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.imageView.setImageResource(event.image)
        holder.textView.text = event.name
        holder.textView2.text = event.date
        holder.textView3.text = event.ticketPrice
        holder.textView4.text = event.location
        holder.textView5.text = event.description

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(event)
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}