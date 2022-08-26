package com.sandip.notesapp.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.text.format.DateFormat.is24HourFormat
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.sandip.notesapp.R
import com.sandip.notesapp.adapter.ViewAdapter
import com.sandip.notesapp.databinding.ActivityAddNoteBinding
import com.sandip.notesapp.model.NoteEntity
import kotlinx.coroutines.launch


//const val CHANNEL_ID: String = "23"
//const val CHANNEL_NAME: String = "Laha"
//const val CHANNEL_DESCRIPTION = "Notification Message"

@Suppress("DEPRECATION")
class AddNote : AppCompatActivity(), ViewAdapter.NoteClickDeleteInterface{


    private lateinit var binding: ActivityAddNoteBinding
    private val REQUEST_IMAGE_CAPTURE = 1
    private val SELECT_PICTURE = 2
    companion object {
        private var allNotes = ArrayList<NoteEntity>()
        private var all = ArrayList<NoteEntity>()

    }
    //    private val AUTOCOMPLETE_REQUEST_CODE = 3



    private lateinit var noteViewModel: com.sandip.notesapp.viewmodel.NoteViewModel
    private var noteID = -1
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        createNotificationChannel()

        binding.back.setOnClickListener {
            finish()
        }


        binding.share.setOnClickListener {
            val title = binding.titleMain.text?.toString()
            val body = binding.body.text?.toString()

            val url = binding.urlLink.text?.toString()
            val date = binding.date.text?.toString()
            val time = binding.time.text?.toString()

            val location = binding.placeInput.text?.toString()
            val img = binding.setImage.drawable
            try {
                val bitmap:Bitmap = img.toBitmap()
                val path: String =
                    MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Title", null)
                val imageUri = Uri.parse(path)
                val i = Intent(Intent.ACTION_SEND)
                i.type = "image/png"
                i.putExtra(Intent.EXTRA_STREAM,imageUri)
                i.putExtra(Intent.EXTRA_SUBJECT, title)
                val body = "$body\n$url\n$date,$time\n$location\nShare from the Notefy App\n"
                i.putExtra(Intent.EXTRA_TEXT, body)
                startActivity(Intent.createChooser(i, "Share with :"))
            } catch (e: Exception) {
                Toast.makeText(this, "Hmm.. Sorry, \nCannot be share", Toast.LENGTH_SHORT).show()
            }
        }


        val colorDialog = Dialog(this)
        colorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        colorDialog.setContentView(R.layout.color_popup)

        val addDialog = Dialog(this)
        addDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addDialog.setContentView(R.layout.add_popup)

        val imageDialog = Dialog(this)
        imageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        imageDialog.setContentView(R.layout.image_popup)

        binding.add.setOnClickListener {
            addDialog.show()
            addDialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            addDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            addDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            addDialog.window?.setGravity(Gravity.BOTTOM)
        }

        binding.color.setOnClickListener {
            colorDialog.show()
            colorDialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            colorDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            colorDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            colorDialog.window?.setGravity(Gravity.BOTTOM)
        }

        binding.image.setOnClickListener {

            imageDialog.show()
            imageDialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            imageDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            imageDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            imageDialog.window?.setGravity(Gravity.BOTTOM)

        }
        val camera: LinearLayout = imageDialog.findViewById(R.id.take_photo)
        val photo: LinearLayout = imageDialog.findViewById(R.id.add_photo)



        camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            imageDialog.dismiss()

        }

        photo.setOnClickListener {
            val i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE)
            imageDialog.dismiss()
        }


        val frame_white: FrameLayout = colorDialog.findViewById(R.id.frame_white)
        val white: ImageView = colorDialog.findViewById(R.id.white)
        white.setImageResource(R.drawable.ic_baseline_done_24)

        val frame_lightsteelblue: FrameLayout = colorDialog.findViewById(R.id.frame_lightsteelblue)
        val lightsteelblue: ImageView = colorDialog.findViewById(R.id.lightsteelblue)

        val frame_aquamarine: FrameLayout = colorDialog.findViewById(R.id.frame_aquamarine)
        val aquamarine: ImageView = colorDialog.findViewById(R.id.aquamarine)

        val frame_grey: FrameLayout = colorDialog.findViewById(R.id.frame_grey)
        val grey: ImageView = colorDialog.findViewById(R.id.grey)

        val frame_darkgrey: FrameLayout = colorDialog.findViewById(R.id.frame_darkgrey)
        val darkgrey: ImageView = colorDialog.findViewById(R.id.darkgrey)

        val frame_lightcyan: FrameLayout = colorDialog.findViewById(R.id.frame_lightcyan)
        val lightcyan: ImageView = colorDialog.findViewById(R.id.lightcyan)

        val frame_lightgoldenyellow: FrameLayout =
            colorDialog.findViewById(R.id.frame_lightgoldenyellow)
        val lightgoldenyellow: ImageView = colorDialog.findViewById(R.id.lightgoldenyellow)

        val frame_lightgreen: FrameLayout = colorDialog.findViewById(R.id.frame_lightgreen)
        val lightgreen: ImageView = colorDialog.findViewById(R.id.lightgreen)

        val frame_palegoldenrod: FrameLayout = colorDialog.findViewById(R.id.frame_palegoldenrod)
        val palegoldenrod: ImageView = colorDialog.findViewById(R.id.palegoldenrod)

        val frame_palevioletred: FrameLayout = colorDialog.findViewById(R.id.frame_palevioletred)
        val palevioletred: ImageView = colorDialog.findViewById(R.id.palevioletred)

        val frame_powderblue: FrameLayout = colorDialog.findViewById(R.id.frame_powderblue)
        val powderblue: ImageView = colorDialog.findViewById(R.id.powderblue)

        val frame_rosybrown: FrameLayout = colorDialog.findViewById(R.id.frame_rosybrown)
        val rosybrown: ImageView = colorDialog.findViewById(R.id.rosybrown)

        val frame_sandybrown: FrameLayout = colorDialog.findViewById(R.id.frame_sandybrown)
        val sandybrown: ImageView = colorDialog.findViewById(R.id.sandybrown)

        val frame_thistle: FrameLayout = colorDialog.findViewById(R.id.frame_thistle)
        val thistle: ImageView = colorDialog.findViewById(R.id.thistle)

        val frame_violet: FrameLayout = colorDialog.findViewById(R.id.frame_violet)
        val violet: ImageView = colorDialog.findViewById(R.id.violet)


        val tickBox: LinearLayout = addDialog.findViewById(R.id.tick_box)
        tickBox.setOnClickListener {
            addDialog.dismiss()
            binding.checkbox.visibility = View.VISIBLE
            binding.addMore.visibility = View.VISIBLE
        }

        binding.addMore.setOnClickListener {

            val linf = LayoutInflater.from(this)

            val rr: LinearLayout = findViewById<View>(R.id.more) as LinearLayout
            val v: View = linf.inflate(R.layout.add_tick, null)
            rr.addView(v)
        }


        val url: LinearLayout = addDialog.findViewById(R.id.add_url)
        url.setOnClickListener {
            addDialog.dismiss()
            showAlert("url")

        }


        //        val apiKey = getString(R.string.api_key)
//        if (!Places.isInitialized()) {
//            Places.initialize(applicationContext, apiKey)
//        }
        val place: LinearLayout = addDialog.findViewById(R.id.place)
        place.setOnClickListener {
            addDialog.dismiss()
            showAlert("place")
//            var binding1: PlaceBinding = PlaceBinding.inflate(layoutInflater)
//            setContentView(binding1.root)
//
//            val autocompleteFragment =
//             supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
//                        as AutocompleteSupportFragment
//
//            // Specify the types of place data to return.
//            autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))
//            Log.d(TAG, "An error occurred:")
//
//            // Set up a PlaceSelectionListener to handle the response.
//            autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
//
//                override fun onPlaceSelected(place: Place) {
////                    binding.place.text = place.name.toString()
////                    binding.place.visibility = View.VISIBLE
//                    Log.i(TAG, "Place: ${place.name}, ${place.id}")
//                }
//
//                override fun onError(p0: Status) {
//                    Log.i(TAG, "An error occurred: $p0")
//                }
//            })
//            Log.d(TAG, "An error occurred:2")
//


        }
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select time")
                .build()

        val reminder: LinearLayout = addDialog.findViewById(R.id.reminder)

        binding.reminder.setOnClickListener {
            addDialog.dismiss()
            datePicker.show(supportFragmentManager, "Date_Picker")
            timePicker.show(supportFragmentManager, "Time_Piker")


        }

        binding.reminder.setOnLongClickListener {
            alertDelete()
            true
        }


        reminder.setOnClickListener {
            addDialog.dismiss()
            datePicker.show(supportFragmentManager, "Date_Picker")
            timePicker.show(supportFragmentManager, "Time_Piker")

        }


        datePicker.addOnPositiveButtonClickListener {
            // Respond to positive button click.
            (datePicker.headerText).also { binding.date.text = it }
            binding.reminder.visibility = View.VISIBLE
//            createNotificationChannel()
//             displaySimpleNotification()

        }
        datePicker.addOnNegativeButtonClickListener {
            // Respond to negative button click.
        }
        datePicker.addOnCancelListener {
            // Respond to cancel button click.
        }
        datePicker.addOnDismissListener {
            // Respond to dismiss events.
        }


        timePicker.addOnPositiveButtonClickListener {
            // call back code
            "${timePicker.hour}:${timePicker.minute}".also { binding.time.text = it }
        }
        timePicker.addOnNegativeButtonClickListener {
            // call back code
        }
        timePicker.addOnCancelListener {
            // call back code
        }
        timePicker.addOnDismissListener {
            // call back code
        }












        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[com.sandip.notesapp.viewmodel.NoteViewModel::class.java]


        val noteType = intent.getStringExtra("noteType")

        if (noteType.equals("Edit")) {
            println("Inside add note")
            val noteTitle = intent.getStringExtra("noteTitle")
//            val noteDescription = intent.getStringExtra("noteDescription")
//            val tickDesc = intent.getStringExtra("tickDesc")
//            val url = intent.getStringExtra("url")
//            val date = intent.getStringExtra("date")
//            val time = intent.getStringExtra("time")
//            val place = intent.getStringExtra("location")
//            val clr = intent.getIntExtra("clr", 0)
//            val byteArray = intent.getByteArrayExtra("image")
//
//            val converters = Converters()
//            val bitmap = byteArray?.let { converters.toBitmap(it) }
            noteID = intent.getIntExtra("noteId", -1)
            println(noteID)



            fetchData()


//
//
//
//                Glide.with(applicationContext).load(bitmap).into(binding.setImage);
        }

        binding.addNote2.setOnClickListener {
            val title = binding.titleMain.text?.toString()
            if ((title != null)
                && (title.isNotEmpty())
            ) {
                addtoDB(noteType)
            } else {

                Snackbar.make(it, "Title can not be empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            }


        }



        binding.deleteIt2.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->

                    noteViewModel.deleteById(noteID)
                    Toast.makeText(this, "Note Deleted", Toast.LENGTH_LONG).show()
                    finish()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }











        binding.location.setOnClickListener {
            val uri = "geo:0,0?q=" + binding.placeInput.text
            val gmmIntentUri =
                Uri.parse(uri)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

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
            binding.addNote.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#B0C4DE"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#7FFFD4"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#E8E9EB"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#A9A9A9"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#E0FFFF"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#FAFAD2"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#CEFAD0"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#EEE8AA"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#FFCBD1"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#B0E0E6"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#BC8F8F"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#F4A460"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#D8BFD8"))
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
            binding.addNote.setBackgroundColor(Color.parseColor("#EFC9FE"))
        }
        binding.checkboxMeat.setOnClickListener {

            binding.tickDesc.paint.isStrikeThruText = binding.checkboxMeat.isChecked
        }
    }
    private fun fetchData() {

        lifecycleScope.launch {
            allNotes = noteViewModel.getDataById(noteID) as ArrayList<NoteEntity>

            with(allNotes) {
                dfgdg()
            }
        }
    }

    private fun dfgdg() {

        runOnUiThread {
            val noteTitle = allNotes[0].title
            val checkMeat = allNotes[0].checkBox
            val noteDescription = allNotes[0].body
            val tickDesc = allNotes[0].tickDesc
            val url = allNotes[0].url
            val date = allNotes[0].date
            val time = allNotes[0].time
            val place = allNotes[0].location
            val clr = allNotes[0].clr
            val bitmap = allNotes[0].image
            binding.titleMain.setText(noteTitle)

            if (!(noteDescription.isNullOrEmpty())) {
                binding.body.setText(noteDescription)
            }

            if (!(tickDesc.isNullOrEmpty())) {
                checkMeat?.let { binding.checkboxMeat.setChecked(it) }
                binding.tickDesc.paint.isStrikeThruText = binding.checkboxMeat.isChecked
                binding.tickDesc.setText(tickDesc)
                binding.checkbox.visibility = View.VISIBLE
            }

            if (!(url.isNullOrEmpty())) {
                binding.urlLink.setText(url)
                binding.urlLayout.visibility = View.VISIBLE
            }

            if (!(date.isNullOrEmpty() && time.isNullOrEmpty())) {
                binding.date.setText(date)
                binding.time.setText(time)
                binding.reminder.visibility = View.VISIBLE
            }


            if (!(place.isNullOrEmpty())) {
                binding.placeInput.setText(place)
                binding.location.visibility = View.VISIBLE
            }

            binding.addNote.setBackgroundColor(clr)





            binding.setImage.visibility = View.VISIBLE
            binding.setImage.setImageBitmap(bitmap)

        }

    }


    private fun addtoDB(noteType: String?) {

        val title = binding.titleMain.text?.toString()
        val body = binding.body.text?.toString()
        val checkBox = binding.checkboxMeat.isChecked
        val tickDesc = binding.tickDesc?.text.toString()
        val url = binding.urlLink.text?.toString()
        val date = binding.date.text?.toString()
        val time = binding.time.text?.toString()

        val location = binding.placeInput.text?.toString()
        val img = binding.setImage.drawable

        val lay = findViewById<View>(R.id.addNote) as ConstraintLayout
        val viewColor = lay.background as ColorDrawable
        val colorId = viewColor.color

        val bitmap: Bitmap? = (img as? BitmapDrawable)?.bitmap
        val height = bitmap?.height
        val width = bitmap?.width
        val bit = bitmap?.let { Bitmap.createScaledBitmap(it, width!!,height!!,false) }


        if (noteType.equals("Edit")){
            lifecycleScope.launch {
                val updatedNote = NoteEntity(title,body,checkBox,tickDesc,url,date,time,location,colorId, bit)//, noteDescription)

                updatedNote.id = noteID
                noteViewModel.updateNote(updatedNote)
            }
            Toast.makeText(this, "Details Updated..", Toast.LENGTH_LONG).show()


        }

        else{


            lifecycleScope.launch {
                noteViewModel.addNote(
                    NoteEntity(
                        title, body,checkBox, tickDesc, url, date, time, location, colorId,
                        bit
                    )
                )//, noteDescription))
                Toast.makeText(applicationContext, "$title Added", Toast.LENGTH_LONG)
                    .show()

            }


        }
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }


    private fun showAlert(s: String) {
        val builder = AlertDialog.Builder(this)
        val input = EditText(this)
        input.inputType = InputType.TYPE_TEXT_VARIATION_URI
        input.background = null
        builder.setView(input)

        if(s=="url"){
            builder.setTitle("URL")
            input.hint = "https://"
            builder.setPositiveButton(
                "OK"
            ) {
                    _, _ ->
                if(input.text.isNotEmpty()) {
                    binding.urlLink.text = input.text.toString()
                    binding.urlLayout.visibility = View.VISIBLE
                }
                else{
                    Toast.makeText(applicationContext, "Please enter a link", Toast.LENGTH_LONG).show()
                    showAlert("url")
                }
            }


        }
        else if(s=="place"){
            builder.setTitle("Place")
            input.hint = ""
            builder.setPositiveButton(
                "OK"
            ) {
                    _, _ ->
                if(input.text.isNotEmpty()) {
                    binding.placeInput.text = input.text.toString()
                    binding.location.visibility = View.VISIBLE
                }
                else{
                    Toast.makeText(applicationContext, "Please enter a place", Toast.LENGTH_LONG).show()
                    showAlert("place")
                }
            }
        }


        builder.setNegativeButton(
            "Cancel"
        ) { dialog, _ -> dialog.cancel() }

        builder.show()    }

    private fun alertDelete() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure to delete the note permanently ?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                Snackbar.make(binding.root, "Note has been deleted", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

//            viewModal.deleteNote(entityPerson)
//            Toast.makeText(this, "${entityPerson.name} Deleted", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.setImage.visibility = View.VISIBLE
        if ((requestCode == REQUEST_IMAGE_CAPTURE) && (resultCode == RESULT_OK)) {

            val imageBitmap = data?.extras?.get("data") as Bitmap
//            Bitmap.createScaledBitmap(imageBitmap, 120,120,false)
            Glide.with(this).load(imageBitmap).into(binding.setImage);

//            binding.setImage.setImageBitmap(imageBitmap)
        } else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            if (null != selectedImageUri) {
                Glide.with(this).load(selectedImageUri).into(binding.setImage);

//                binding.setImage.setImageURI(selectedImageUri)
            }
        }
        else {
            binding.setImage.visibility = View.GONE
        }


    }

    override fun onDeleteIconClick(entityPerson: NoteEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->

                noteViewModel.deleteNote(entityPerson)
                Toast.makeText(this, "${entityPerson.title} Deleted", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()    }


    //    private fun createNotificationChannel() {
//        //Create Notification channel for SDK above 25
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel =
//                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
//            channel.description = CHANNEL_DESCRIPTION
//            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun displaySimpleNotification() {
//
//        val notificationIntent = Intent(this, Notification::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingNotificationIntent: PendingIntent = PendingIntent.getBroadcast(
//            this,
//            0,
//            notificationIntent,
//            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val title = binding.title.text.toString()
//        val message = binding.body.text.toString()
//        notificationIntent.putExtra(titleExtra, title)
//        notificationIntent.putExtra(messageExtra, message)
////        val titleStatusChangeListener= binding.datePicker.year
//
//        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val time = getTime()
//        alarmManager.setExactAndAllowWhileIdle(
//            AlarmManager.RTC_WAKEUP,
//            time,
//            pendingNotificationIntent
//        )
//        showAlert(
//            time,
//            title,
//            message
//        )
//    }
//
//    private fun showAlert(time: Long, title: String, message: String) {
//        val date = Date(time)
//        val dateFormat = android.text.format.DateFormat.getLongDateFormat(this)
//        val timeFormat = android.text.format.DateFormat.getTimeFormat(this)
//
//        android.app.AlertDialog.Builder(this)
//            .setTitle("")
//            .setMessage(
//                "Title: " + title + "\nMessage " + message + "\nAt " + dateFormat.format(
//                    date
//                ) + " " + timeFormat.format(date)
//            )
//            .setPositiveButton("Okay") { _, _ -> }
//            .show()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun getTime(): Long {
//
////        val date = binding.date.text
////        val delim = " "
////        val list = date.split(delim)
////
////
////
////        val time = binding.time.text
////        val delim2 = ":"
////        val list2 = time.split(delim2)
////
////
////        val calendar = Calendar.getInstance()
////        calendar.set(2022,8,22,hour, minute)
////        return calendar.timeInMillis
//
//
//        val minute = binding.timePicker.minute
//        val hour = binding.timePicker.hour
//        val day = binding.datePicker.dayOfMonth
//        val month = binding.datePicker.month
//        val year = binding.datePicker.year
//
//        val calendar = Calendar.getInstance()
//        calendar.set(year, month, day, hour, minute)
//        return calendar.timeInMillis
//    }

}
