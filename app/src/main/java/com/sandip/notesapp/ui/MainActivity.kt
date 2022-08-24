package com.sandip.notesapp.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sandip.notesapp.R
import com.sandip.notesapp.adapter.ViewAdapter
import com.sandip.notesapp.databinding.ActivityMainBinding
import com.sandip.notesapp.model.NoteEntity
import com.sandip.notesapp.viewmodel.NoteViewModel
import java.util.*


class MainActivity : AppCompatActivity(), ViewAdapter.NoteClickInterface, ViewAdapter.NoteClickDeleteInterface {

    //Declaration of View Binding, ViewModel and Adapter instances
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModal: NoteViewModel
    private lateinit var noteRVAdapter : ViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Dialog Popup for Sort by Button
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_popup)

        //OnClickListener to Open Sort by Dialog Popup
        binding.appBarMain.toolbar.sortBy.setOnClickListener {
            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.BOTTOM)
        }

        //OnClickListener to Open Navigation Drawer
        binding.appBarMain.toolbar.userPhoto.setOnClickListener {
            val drawerLayout: DrawerLayout = binding.drawerLayout
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        //OnClickListener to Add a New Note
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


        layout1.setOnClickListener {
            image1.setImageResource(R.drawable.ic_baseline_done_24)
            image1.setImageResource(R.drawable.ic_baseline_done_24)
            image2.setImageResource(0)
            image3.setImageResource(0)
            image4.setImageResource(0)


        }

        layout2.setOnClickListener {
            image2.setImageResource(R.drawable.ic_baseline_done_24)
            image1.setImageResource(0)
            image3.setImageResource(0)
            image4.setImageResource(0)


        }

        layout3.setOnClickListener {
            image3.setImageResource(R.drawable.ic_baseline_done_24)
            image1.setImageResource(0)
            image2.setImageResource(0)
            image4.setImageResource(0)

        }

        layout4.setOnClickListener {
            image4.setImageResource(R.drawable.ic_baseline_done_24)
            image1.setImageResource(0)
            image2.setImageResource(0)
            image3.setImageResource(0)

        }

        image2.setImageResource(R.drawable.ic_baseline_done_24)

        val notesRV = binding.appBarMain.recyclerView
        notesRV.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        noteRVAdapter = ViewAdapter(this, this)
        notesRV.adapter = noteRVAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]


        viewModal.allData.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(it)
                noteRVAdapter.setData(it)

            }
        }


        binding.appBarMain.toolbar.searchView2.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    getItemsFromDb(p0)
                }
                return true
            }



            override fun onQueryTextChange(p0: String?): Boolean {

                if (p0 != null) {
                    getItemsFromDb(p0)
                }
                Log.d("value",p0.toString())
                return true
            }

        })
    }


    override fun onDeleteIconClick(entityPerson: NoteEntity) {
        TODO("Not yet implemented")
    }

    override fun onNoteClick(entityPerson: NoteEntity) {
        val intent = Intent(this@MainActivity, AddNote::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", entityPerson.title)
        intent.putExtra("noteDescription", entityPerson.body)
        intent.putExtra("tickDesc", entityPerson.tickDesc)
        intent.putExtra("url", entityPerson.url)
        intent.putExtra("date", entityPerson.date)
        intent.putExtra("time", entityPerson.time)
        intent.putExtra("location", entityPerson.location)
        intent.putExtra("clr", entityPerson.clr)
//        val stream = ByteArrayOutputStream()
//        entityPerson.image?.compress(Bitmap.CompressFormat.PNG, 100, stream)
//        val byteArray: ByteArray = stream.toByteArray()
//        intent.putExtra("image", byteArray)
        intent.putExtra("noteId", entityPerson.id)
        startActivity(intent)    }

    private fun getItemsFromDb(p0: String) {
        var searchText = "%$p0%"
        Log.d("value", searchText)

        viewModal.searchDatabase(searchText).observe(this@MainActivity) { list ->
            list?.let {
                noteRVAdapter.setData(it)
                Log.d("res", it.toString())

            }

        }
    }
}
