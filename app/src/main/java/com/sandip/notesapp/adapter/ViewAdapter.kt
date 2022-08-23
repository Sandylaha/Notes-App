package com.sandip.notesapp.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sandip.notesapp.R
import com.sandip.notesapp.model.NoteEntity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewAdapter(
    private val noteClickInterface: NoteClickInterface,
    private val noteClickDeleteInterface: NoteClickDeleteInterface,
):
    RecyclerView.Adapter<ViewAdapter.ViewHolder>() {

    private var allNotes = ArrayList<NoteEntity>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titlecard)
        val body: TextView = itemView.findViewById(R.id.body)
        val tickDesc: TextView = itemView.findViewById(R.id.tick_Desc)
        val url: TextView = itemView.findViewById(R.id.urlLink)
        var date: TextView = itemView.findViewById(R.id.date)
        val time: TextView = itemView.findViewById(R.id.time)
        val location: TextView = itemView.findViewById(R.id.place)
        val clr: FrameLayout = itemView.findViewById(R.id.clr)
        var image: ImageView = itemView.findViewById(R.id.img22)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = allNotes[position].title
        holder.body.text = allNotes[position].body
        holder.tickDesc.text = allNotes[position].tickDesc
        holder.url.text = allNotes[position].url
        holder.date.text = allNotes[position].date
        holder.time.text = allNotes[position].time
        holder.location.text = allNotes[position].location
        holder.clr.setBackgroundColor(allNotes[position].clr)
        holder.image.setImageBitmap(allNotes[position].image)

//        holder.deleteIV.setOnClickListener {
//            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
//        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }


    fun setData(arrNotesList: List<NoteEntity>) {
        allNotes = arrNotesList as ArrayList<NoteEntity>
        notifyDataSetChanged()

    }


    fun updateList(newList: List<NoteEntity>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    interface NoteClickDeleteInterface {
        fun onDeleteIconClick(entityPerson: NoteEntity)
    }

    interface NoteClickInterface {
        fun onNoteClick(entityPerson: NoteEntity)
    }
}