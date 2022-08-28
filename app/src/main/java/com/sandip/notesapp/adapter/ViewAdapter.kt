package com.sandip.notesapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandip.notesapp.R
import com.sandip.notesapp.model.NoteEntity


class ViewAdapter(
    private val activity: Activity,
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
//                val checkBox : CheckBox = itemView.findViewById(R.id.check_meat)
//        val tickDesc: TextView = itemView.findViewById(R.id.tDesc)
//        val t : TextView = itemView.findViewById(R.id.sandip)
//        val checkBox : CheckBox = itemView.findViewById(R.id.laha)

        val nested : RecyclerView = itemView.findViewById(R.id.listview3)


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
        if (!(allNotes[position].body.isNullOrEmpty())) {
            holder.body.text = allNotes[position].body
            holder.body.visibility = View.VISIBLE
        }
////        if(!(allNotes[position].t.isNullOrEmpty())) {
////            allNotes[position].c.also {
////                if (it != null) {
////                    holder.checkBox.isChecked = allNotes[position].c?.get(0) ?: false
////                }
////            }
//            val size = allNotes[position].t?.size
//        for(i in 0 until size!!){
//            holder.checkBox.isChecked = allNotes[position].c?.get(i) ?: false
//
//            holder.tickDesc.append(allNotes[position].t?.get(i) ?: null)
//            holder.tickDesc.append("\n")
//        }
////            holder.tickDesc.text = allNotes[position].t?.get(position) ?: null
////            if(allNotes[position].c == true){
//                holder.tickDesc.paint.isStrikeThruText = true
////            }
//            holder.tick.visibility = View.VISIBLE
////        }
        if (!(allNotes[position].url.isNullOrEmpty())) {
            holder.url.text = allNotes[position].url
            holder.url.visibility = View.VISIBLE
        }
        if (!(allNotes[position].date.isNullOrEmpty() && allNotes[position].time.isNullOrEmpty())) {
            holder.date.text = allNotes[position].date
            holder.time.text = allNotes[position].time
            holder.reminder.visibility = View.VISIBLE

        }

        if (!(allNotes[position].location.isNullOrEmpty())) {
            holder.place.text = allNotes[position].location
            holder.location.visibility = View.VISIBLE
        }
        holder.clr.setBackgroundColor(allNotes[position].clr)


        if ((allNotes[position].image != null)) {
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

//Working Code
          var  childAdapter = ChildAdapter(activity,
                allNotes[position].t,
                allNotes[position].c
            )
            val notesRV = holder.nested
            notesRV.layoutManager = LinearLayoutManager(activity)

            notesRV.adapter = childAdapter
//
//  Upto here
//        val textView = TextView(activity)
//       var size = allNotes[position].t?.size
//       for (i in 0 until size!!) {
//
//        textView.layoutParams =
//            LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//        textView.gravity = Gravity.LEFT
//           textView.append(allNotes[position].t?.get(i) ?: " ")
//           textView.append(" ")
//        holder.tick.addView(textView)
//       }


//            holder.tickDesc.text =  allNotes[position].t?.get(i) ?: "not"
//            holder.checkBox.isChecked = allNotes[position].c?.get(i) ?: false
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