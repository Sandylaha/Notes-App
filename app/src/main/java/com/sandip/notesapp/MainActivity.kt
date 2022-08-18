package com.sandip.notesapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.snackbar.Snackbar
import com.sandip.notesapp.databinding.ActivityMainBinding
import com.sandip.notesapp.databinding.BottomPopupBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var binding2: BottomPopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding2 = BottomPopupBinding.inflate(layoutInflater)

        binding.appBarMain.userPhoto.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            val drawerLayout: DrawerLayout = binding.drawerLayout
            drawerLayout.openDrawer(Gravity.LEFT)

        }

        binding.appBarMain.sortBy.setOnClickListener {


            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.bottom_popup)
            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.BOTTOM)
        }

//        val radio: RadioButton = findViewById(R.id.aa)
//       radio?.setOnCheckedChangeListener { radioGroup, i ->
//           val rb = findViewById<View>(i) as RadioButton
//
//           Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
//       }


    }
    }


