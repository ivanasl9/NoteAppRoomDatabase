package com.example.noteapproomdatabase

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val context: MainActivity,
    private val noteClickInterface: noteClickInterface,
    private val noteClickDeleteInterface: noteClickDeleteInterface,
    private val allNotes: ArrayList<Note> = ArrayList()


) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        val deleteOption: ImageView = itemView.findViewById(R.id.deleteOption)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = allNotes[position].noteTitle
        holder.timestamp.text =
            context.getString(R.string.lastUpdate) + allNotes[position].timeStamp

        holder.deleteOption.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onIconClick(allNotes[position])
        }
    }
}

interface noteClickInterface {
    fun onIconClick(note: Note)
}

interface noteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}



