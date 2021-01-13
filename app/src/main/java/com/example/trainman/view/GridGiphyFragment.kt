package com.example.trainman.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.trainman.R
import com.example.trainman.adapter.GiphyAdapter
import com.example.trainman.db.AppDatabase
import com.example.trainman.utilities.isInternetAvailable
import com.example.trainman.utilities.toast
import com.example.trainman.viewmodel.GiphyViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates


class GridGiphyFragment : Fragment(), GiphyAdapter.OnItemClickListener {
    private val logTag = GridGiphyFragment::class.java.simpleName
    private val viewModel: GiphyViewModel by viewModel()
    lateinit var progressView: ProgressBar
    lateinit var adapter: GiphyAdapter
    lateinit var giphyRcv: RecyclerView
    lateinit var loadMore: MaterialButton
    lateinit var db: AppDatabase
    private var offset = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = AppDatabase.invoke(requireContext())
        return inflater.inflate(R.layout.fragment_grid_giphy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressView = view.findViewById(R.id.progressView)!!
        giphyRcv = view.findViewById(R.id.recyclerView)!!
        loadMore = view.findViewById(R.id.loadBtn)!!
        adapter = GiphyAdapter(this)
        giphyRcv.adapter = adapter
        setObserver()
        loadMore.setOnClickListener {
            if (isInternetAvailable(requireContext())) {
                offset++
                viewModel.callApi(offset)
                loadMore.isVisible = false
            } else context?.toast("No internet connection")
        }
        getLocalData()
    }

    private fun getLocalData() {
        val data = db.giphyDao()?.getAll()
        if (data?.data != null) {
            adapter.list = data.data
        } else {
            if (isInternetAvailable(requireContext()))
                viewModel.callApi(0)
            else context?.toast("No internet connection")
        }
    }

    private fun setObserver() {
        viewModel.giphyData().observe(viewLifecycleOwner, Observer {
            if (offset == 0) {
                db.giphyDao()?.insertAll(it)
                getLocalData()
            } else adapter.appendList(it?.data!!)
            offset++
        })

        viewModel.errorMessage().observe(viewLifecycleOwner, Observer {
            Log.e(logTag, "error message $it")
            loadMore.isVisible = true
            context?.toast(it)
        })

        viewModel.errorResponse().observe(viewLifecycleOwner, Observer {
            loadMore.isVisible = true
            Log.e(logTag, "error body: ${it.errorBody()}")
        })

        viewModel.isLoading().observe(viewLifecycleOwner, Observer {
            progressView.isVisible = it
            loadMore.isVisible = !it
        })
    }

    override fun onItemSelect(url: String, name: String) {
        val bundle = bundleOf("url" to url, "name" to name)
        findNavController().navigate(
            R.id.action_gridGiphyFragment_to_fullViewFragment,
            bundle
        )
    }
}