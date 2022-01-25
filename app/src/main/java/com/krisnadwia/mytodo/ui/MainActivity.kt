package com.krisnadwia.mytodo.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.krisnadwia.mytodo.R
import com.krisnadwia.mytodo.adapter.NoteAdapter
import com.krisnadwia.mytodo.entity.Note
import com.krisnadwia.mytodo.viewmodal.NotesViewModal

class MainActivity : AppCompatActivity(), NoteAdapter.INotesRVAdapter {
    private lateinit var rvNotes: RecyclerView
    private lateinit var llDummy: LinearLayout
    private lateinit var btnAdd: Button
    private lateinit var etNotes: EditText

    private lateinit var notesViewModal: NotesViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNotes = findViewById(R.id.rv_notes)
        llDummy = findViewById(R.id.ll_dummy)
        btnAdd = findViewById(R.id.btn_add)
        etNotes = findViewById(R.id.et_notes)

        val adapter = NoteAdapter(this, this)
        rvNotes.adapter = adapter
        notesViewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesViewModal::class.java]
        notesViewModal.allNotes.observe(this, { list ->
            if (list.isEmpty()) {
                rvNotes.visibility = View.INVISIBLE
                llDummy.visibility = View.VISIBLE
            } else {
                rvNotes.visibility = View.VISIBLE
                llDummy.visibility = View.INVISIBLE
                adapter.updateTodo(list)
            }

            list?.let {
                rvNotes.visibility = View.VISIBLE
                adapter.updateTodo(it)
            }
        })

        btnAdd.setOnClickListener {
            val itext = etNotes.text.toString()
            if (itext.isNotEmpty()) {
                notesViewModal.insertNotes(Note(itext))
                etNotes.setText("")
            } else {
                Toast.makeText(this, "field is empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemClick(notes: Note) {
        notesViewModal.deleteNotes(notes)
    }
}