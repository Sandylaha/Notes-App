package com.sandip.notesapp.ui.appbarmain

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandip.notesapp.R

class AppBarMain : Fragment() {

    companion object {
        fun newInstance() = AppBarMain()
    }

    private lateinit var viewModel: AppBarMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_bar_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppBarMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}