package com.sandip.notesapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.text.format.DateFormat.is24HourFormat
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.sandip.notesapp.databinding.ActivityAddNoteBinding


open class AddNote : AppCompatActivity() {


    private lateinit var binding: ActivityAddNoteBinding
    private val REQUEST_IMAGE_CAPTURE = 1
    private val SELECT_PICTURE = 2
    private val AUTOCOMPLETE_REQUEST_CODE = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            finish()
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
            alertDelete()
        }



        binding.addNote2.setOnClickListener {


            finish()
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

            var linf: LayoutInflater
            val rr: LinearLayout

            linf = applicationContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            ) as LayoutInflater
            linf = LayoutInflater.from(this)

            rr = findViewById<View>(R.id.more) as LinearLayout


            val v: View = linf.inflate(R.layout.add_tick, null)
            rr.addView(v)
        }



        val url: LinearLayout = addDialog.findViewById(R.id.add_url)
        url.setOnClickListener {
            addDialog.dismiss()
            showAlert()

        }



        //        val apiKey = getString(R.string.api_key)
//        if (!Places.isInitialized()) {
//            Places.initialize(applicationContext, apiKey)
//        }
//        val place: LinearLayout = addDialog.findViewById(R.id.place)
//        place.setOnClickListener {
//            addDialog.dismiss()
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
//        }
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
            timePicker.show(supportFragmentManager, "Time_Piker");
        }

        binding.reminder.setOnLongClickListener {
            alertDelete()
            true
        }


        reminder.setOnClickListener {
            addDialog.dismiss()
            datePicker.show(supportFragmentManager, "Date_Picker")
            timePicker.show(supportFragmentManager, "Time_Piker");

        }


        datePicker.addOnPositiveButtonClickListener {
            // Respond to positive button click.
            binding.date.text = datePicker.headerText+","
            binding.reminder.visibility = View.VISIBLE

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
            binding.time.text = "${timePicker.hour}:${timePicker.minute}"
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
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("URL")

        val input = EditText(this)

        input.inputType = InputType.TYPE_TEXT_VARIATION_URI
        input.hint = "https://"
        input.background = null
        builder.setView(input)

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
                showAlert()
            }
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.cancel() }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.setImage.visibility = View.VISIBLE
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.setImage.setImageBitmap(imageBitmap)
        } else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            if (null != selectedImageUri) {
                binding.setImage.setImageURI(selectedImageUri)
            }
        }
        else {
            binding.setImage.visibility = View.GONE
        }

    }

}
