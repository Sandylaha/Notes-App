package com.sandip.notesapp.adapter/*
    Code written by IJApps
    github.com/IJ-Apps
    Tutorial Series: https://www.youtube.com/watch?v=9nFGR8dIu_w&list=PLLmkb5CTw5rRsR6reO-ZkbE-QJF-GstwG
*/
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.sandip.notesapp.R
import com.sandip.notesapp.ui.AddNote

class Adapter(context: Context, var checks: ArrayList<String>?, var list: ArrayList<Boolean>?) :
    ArrayAdapter<String?>(context, R.layout.note_todo, list as ArrayList<String?>) {

    // The method we override to provide our own layout for each View (row) in the ListView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val mInflater =
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = mInflater.inflate(R.layout.note_todo, null)
//            val name:CheckBox = convertView!!.findViewById<CheckBox>(R.id.laha)
            val add:TextView = convertView.findViewById<TextView>(R.id.sandip)
//            val remove = convertView.findViewById<ImageView>(R.id.rmve)
            var size = checks?.size


            for (i in 0 until size!!) {
//                name.isChecked = list?.get(i) ?: false
                add.text = checks?.get(i) ?: "Null"

            }
            // Listeners for duplicating and removing an item.
            // They use the static removeItem and addItem methods created in MainActivity.
//            remove.setOnClickListener { AddNote.removeItem(position)
//            }
//            AddNote.getPosition(position)

            // Listeners for duplicating and removing an item.
            // They use the static removeItem and addItem methods created in MainActivity.
        }
        return convertView!!
    }
}