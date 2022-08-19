package com.sandip.notesapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sandip.notesapp.databinding.ActivityAddNoteBinding
import com.sandip.notesapp.databinding.ColorPopupBinding


class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            finish()
        }

        binding.delete.setOnClickListener {
        }
        binding.share.setOnClickListener {

            try {
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plan"
//                i.putExtra(Intent.EXTRA_SUBJECT, mSource)
//                val body = "$mTitle\n$mUrl\nShare from the News App\n"
//                i.putExtra(Intent.EXTRA_TEXT, body)
                startActivity(Intent.createChooser(i, "Share with :"))
            } catch (e: Exception) {
                Toast.makeText(this, "Hmm.. Sorry, \nCannot be share", Toast.LENGTH_SHORT).show()
            }
        }
        binding.delete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure to delete the note permanently ?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->
                    Snackbar.make(binding.root, "Note has been deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show()

//            viewModal.deleteNote(entityPerson)
//            Toast.makeText(this, "${entityPerson.name} Deleted", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }



        binding.addNote2.setOnClickListener {



            finish()
        }

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.color_popup)
        binding.add.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.add_popup)
            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.BOTTOM)
        }

        binding.color.setOnClickListener {
            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.BOTTOM)


        }
        val frame_white: FrameLayout = dialog.findViewById(R.id.frame_white)
        val white:ImageView  = dialog.findViewById(R.id.white)
        white.setImageResource(R.drawable.ic_baseline_done_24)

        val frame_lightsteelblue: FrameLayout = dialog.findViewById(R.id.frame_lightsteelblue)
        val lightsteelblue:ImageView  = dialog.findViewById(R.id.lightsteelblue)

        val frame_aquamarine: FrameLayout = dialog.findViewById(R.id.frame_aquamarine)
        val aquamarine:ImageView  = dialog.findViewById(R.id.aquamarine)

        val frame_grey: FrameLayout = dialog.findViewById(R.id.frame_grey)
        val grey:ImageView  = dialog.findViewById(R.id.grey)

        val frame_darkgrey: FrameLayout = dialog.findViewById(R.id.frame_darkgrey)
        val darkgrey:ImageView  = dialog.findViewById(R.id.darkgrey)

        val frame_lightcyan: FrameLayout = dialog.findViewById(R.id.frame_lightcyan)
        val lightcyan:ImageView  = dialog.findViewById(R.id.lightcyan)

        val frame_lightgoldenyellow: FrameLayout = dialog.findViewById(R.id.frame_lightgoldenyellow)
        val lightgoldenyellow:ImageView  = dialog.findViewById(R.id.lightgoldenyellow)

        val frame_lightgreen: FrameLayout = dialog.findViewById(R.id.frame_lightgreen)
        val lightgreen:ImageView  = dialog.findViewById(R.id.lightgreen)

        val frame_palegoldenrod: FrameLayout = dialog.findViewById(R.id.frame_palegoldenrod)
        val palegoldenrod:ImageView  = dialog.findViewById(R.id.palegoldenrod)

        val frame_palevioletred: FrameLayout = dialog.findViewById(R.id.frame_palevioletred)
        val palevioletred:ImageView  = dialog.findViewById(R.id.palevioletred)

        val frame_powderblue: FrameLayout = dialog.findViewById(R.id.frame_powderblue)
        val powderblue:ImageView  = dialog.findViewById(R.id.powderblue)

        val frame_rosybrown: FrameLayout = dialog.findViewById(R.id.frame_rosybrown)
        val rosybrown:ImageView  = dialog.findViewById(R.id.rosybrown)

        val frame_sandybrown: FrameLayout = dialog.findViewById(R.id.frame_sandybrown)
        val sandybrown:ImageView  = dialog.findViewById(R.id.sandybrown)

        val frame_thistle: FrameLayout = dialog.findViewById(R.id.frame_thistle)
        val thistle:ImageView  = dialog.findViewById(R.id.thistle)

        val frame_violet: FrameLayout = dialog.findViewById(R.id.frame_violet)
        val violet:ImageView  = dialog.findViewById(R.id.violet)


        frame_white.setOnClickListener {
            white.setImageResource(R.drawable.ic_baseline_done_24)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_lightsteelblue.setOnClickListener {
            lightsteelblue.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_aquamarine.setOnClickListener {
            aquamarine.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_grey.setOnClickListener {
            grey.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_darkgrey.setOnClickListener {
            darkgrey.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_lightcyan.setOnClickListener {
            lightcyan.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_lightgoldenyellow.setOnClickListener {
            lightgoldenyellow.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_lightgreen.setOnClickListener {
            lightgreen.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_palegoldenrod.setOnClickListener {
            palegoldenrod.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_palevioletred.setOnClickListener {
            palevioletred.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_powderblue.setOnClickListener {
            powderblue.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_rosybrown.setOnClickListener {
            rosybrown.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_sandybrown.setOnClickListener {
            sandybrown.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            thistle.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_thistle.setOnClickListener {
            thistle.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            violet.setImageResource(0)
        }

        frame_violet.setOnClickListener {
            violet.setImageResource(R.drawable.ic_baseline_done_24)
            white.setImageResource(0)
            lightsteelblue.setImageResource(0)
            aquamarine.setImageResource(0)
            grey.setImageResource(0)
            darkgrey.setImageResource(0)
            lightcyan.setImageResource(0)
            lightgoldenyellow.setImageResource(0)
            lightgreen.setImageResource(0)
            palegoldenrod.setImageResource(0)
            palevioletred.setImageResource(0)
            powderblue.setImageResource(0)
            rosybrown.setImageResource(0)
            sandybrown.setImageResource(0)
            thistle.setImageResource(0)
        }


    }
}