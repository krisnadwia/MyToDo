package com.krisnadwia.mytodo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krisnadwia.mytodo.R
import com.krisnadwia.mytodo.entity.Note

class NoteAdapter(private val context: Context, private val listener: INotesRVAdapter) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =
            NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_item, parent, false))

        viewHolder.ivDelete.setOnClickListener {
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.tvNotes.text = currentNote.text
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTodo(newNote: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newNote)
        notifyDataSetChanged()
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNotes: TextView = itemView.findViewById(R.id.tv_notes)
        var ivDelete: ImageView = itemView.findViewById(R.id.iv_delete)
    }

    interface INotesRVAdapter {
        fun onItemClick(notes: Note)
    }
}