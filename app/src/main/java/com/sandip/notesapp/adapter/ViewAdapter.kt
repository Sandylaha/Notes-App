package com.sandip.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.sandip.notesapp.R
import com.sandip.notesapp.model.NoteEntity
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
        val url: TextView = itemView.findViewById(R.id.urlLink)
        var date: TextView = itemView.findViewById(R.id.date)
        val time: TextView = itemView.findViewById(R.id.time)
        val place: TextView = itemView.findViewById(R.id.place)
        val clr: FrameLayout = itemView.findViewById(R.id.clr)
        var image: ImageView = itemView.findViewById(R.id.img22)

        val imgFrame : LinearLayout = itemView.findViewById(R.id.imgFrame)
        val reminder : LinearLayout = itemView.findViewById(R.id.reminder1)
        val location : LinearLayout = itemView.findViewById(R.id.location1)

        val tick : LinearLayout = itemView.findViewById(R.id.tick)
        val checkBox : CheckBox = itemView.findViewById(R.id.checkbox_meat)
        val tickDesc: TextView = itemView.findViewById(R.id.tick_Desc)


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
        if(!(allNotes[position].body.isNullOrEmpty())){
            holder.body.text = allNotes[position].body
            holder.body.visibility = View.VISIBLE
        }
        if(!(allNotes[position].tickDesc.isNullOrEmpty())) {
            allNotes[position].checkBox.also {
                if (it != null) {
                    holder.checkBox.isChecked = it
                }
            }

            holder.tickDesc.text = allNotes[position].tickDesc
            if(allNotes[position].checkBox == true){
                holder.tickDesc.paint.isStrikeThruText = true
            }
            holder.tick.visibility = View.VISIBLE
        }
        if(!(allNotes[position].url.isNullOrEmpty())) {
            holder.url.text = allNotes[position].url
            holder.url.visibility = View.VISIBLE
        }
        if(!(allNotes[position].date.isNullOrEmpty() &&  allNotes[position].time.isNullOrEmpty())) {
            holder.date.text = allNotes[position].date
            holder.time.text = allNotes[position].time
            holder.reminder.visibility = View.VISIBLE

        }

        if(!(allNotes[position].location.isNullOrEmpty())) {
            holder.place.text = allNotes[position].location
            holder.location.visibility = View.VISIBLE
        }
        holder.clr.setBackgroundColor(allNotes[position].clr)


        if((allNotes[position].image != null)) {
//            Picasso.get()
//                .load(allNotes[position].image.toString())
//                .resize(100, 100)
//                .centerCrop()
//                .into(holder.image)
            holder.image.setImageBitmap(allNotes[position].image)
            holder.imgFrame.visibility = View.VISIBLE

        }


        holder.itemView.setOnLongClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
            true
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }


        holder.itemView.setOnLongClickListener {



            true

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
        fun onNoteClick(entityPerson: NoteEntity,)
    }


}