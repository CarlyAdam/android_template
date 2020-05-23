package com.carlyadam.template.ui.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlyadam.template.data.model.Album
import com.carlyadam.template.databinding.ActivityAlbumBinding
import com.carlyadam.template.ui.BaseActivity
import com.carlyadam.template.ui.adapter.AlbumAdapter
import com.carlyadam.template.viewmodel.AlbumViewModel
import org.koin.android.ext.android.inject

class AlbumActivity : BaseActivity(), AlbumAdapter.AdapterListener {
    private val albumViewModel: AlbumViewModel by inject()
    private lateinit var albumAdapter: AlbumAdapter
    private var albumList = ArrayList<Album>()
    private lateinit var binding: ActivityAlbumBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        albumViewModel.getAlbums()

        albumViewModel.responseLiveData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            albumList.addAll(it)
            albumAdapter.submitList(albumList)
        })
        albumViewModel.errorLiveData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            showToast(it)
        })
    }

    private fun initRecyclerView() {
        albumAdapter = AlbumAdapter(albumList, this,this)
        binding.recyclerViewBook.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewBook.adapter = albumAdapter

    }

    override fun onItemTap(position: Int) {
        showToast(albumList[position].id.toString())
    }

}
