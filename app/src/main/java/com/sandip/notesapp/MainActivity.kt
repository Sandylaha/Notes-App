package com.sandip.notesapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.snackbar.Snackbar
import com.sandip.notesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarMain.userPhoto.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            val drawerLayout: DrawerLayout = binding.drawerLayout
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_popup)
        binding.appBarMain.sortBy.setOnClickListener {

            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.BOTTOM)
        }

        binding.appBarMain.addNote2.setOnClickListener {
            startActivity(Intent(applicationContext, AddNote::class.java))
        }

        val layout1: LinearLayout = dialog.findViewById(R.id.layout1)
        val image1: ImageView = dialog.findViewById(R.id.image1)

        val layout2: LinearLayout = dialog.findViewById(R.id.layout2)
        val image2: ImageView = dialog.findViewById(R.id.image2)

        val layout3: LinearLayout = dialog.findViewById(R.id.layout3)
        val image3: ImageView = dialog.findViewById(R.id.image3)

        val layout4: LinearLayout = dialog.findViewById(R.id.layout4)
        val image4: ImageView = dialog.findViewById(R.id.image4)


        layout1.setOnClickListener {view ->
        image1.setImageResource(R.drawable.ic_baseline_done_24)
            image1.setImageResource(R.drawable.ic_baseline_done_24)
            image2.setImageResource(0)
            image3.setImageResource(0)
            image4.setImageResource(0)


        }

        layout2.setOnClickListener {view ->
            image2.setImageResource(R.drawable.ic_baseline_done_24)
            image1.setImageResource(0)
            image3.setImageResource(0)
            image4.setImageResource(0)


        }

        layout3.setOnClickListener {view ->
            image3.setImageResource(R.drawable.ic_baseline_done_24)
            image1.setImageResource(0)
            image2.setImageResource(0)
            image4.setImageResource(0)

        }

        layout4.setOnClickListener {view ->
            image4.setImageResource(R.drawable.ic_baseline_done_24)
            image1.setImageResource(0)
            image2.setImageResource(0)
            image3.setImageResource(0)

        }


    }
}
