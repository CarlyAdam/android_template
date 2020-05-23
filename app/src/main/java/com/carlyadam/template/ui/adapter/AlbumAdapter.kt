package com.carlyadam.template.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlyadam.template.data.model.Album
import com.carlyadam.template.databinding.ItemAlbumBinding

class AlbumAdapter(
    var albumList: List<Album>,
    var mContext: Context,
    var listener: AdapterListener
) :
    ListAdapter<Album, AlbumAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            holder.bind(albumList[position])
            holder.binding.textViewTitle.setOnClickListener {
                listener.onItemTap(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    class ViewHolder(val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.textViewTitle.text = album.title
        }
    }

    interface AdapterListener {
        fun onItemTap(position: Int)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}