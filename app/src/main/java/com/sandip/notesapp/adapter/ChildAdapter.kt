package com.sandip.notesapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.sandip.notesapp.R
import java.util.*


class ChildAdapter(ac: Activity, s: ArrayList<String>?, b: ArrayList<Boolean>?) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    var activity:Activity = ac
    private var check= b
    private var title = s


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tick : TextView = itemView.findViewById(R.id.sandip)
        val ld : LinearLayout = itemView.findViewById(R.id.ld)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_todo,
            parent, false
        )
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var size = title?.size
//
        for(i in 0 until size!!){
//        if (!(childNotes[position].t.isNullOrEmpty())) {
//            childNotes[position].c.also {
//                if (it != null) {
            var imagebyCode = ImageView(activity)
            if(check?.get(i) == true){
                imagebyCode.setImageResource(R.drawable.ic_outline_check_box_24)

            }
            else{
                imagebyCode.setImageResource(R.drawable.ic_outline_check_box_outline_blank_24)

            }
            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(40,40)

            imagebyCode.layoutParams = params
            val myLayout = holder.ld
            myLayout.addView(imagebyCode)

            holder.tick.append(title?.get(i) ?: "")
            holder.tick.append("\n")
        }

//                }

//                holder.tickDesc.append(allNotes[position].t?.get(i) ?: null)
//                holder.tickDesc.append("\n")
//            }
//            holder.tickDesc.text = allNotes[position].t?.get(position) ?: null
//            if(allNotes[position].c == true){
//            holder.tickDesc.paint.isStrikeThruText = true
//            }
//            holder.tick.visibility = View.VISIBLE
//        }
    }

    override fun getItemCount(): Int {
        return 1    }

}