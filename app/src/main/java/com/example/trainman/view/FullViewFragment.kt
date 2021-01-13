package com.example.trainman.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.trainman.R
import com.example.trainman.databinding.FragmentFullViewBinding


class FullViewFragment : Fragment() {
    lateinit var dataBinder: FragmentFullViewBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinder =
            DataBindingUtil.inflate(inflater, R.layout.fragment_full_view, container, false)
        return dataBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinder.url = arguments?.getString("url")
        dataBinder.name = arguments?.getString("name")
    }
}