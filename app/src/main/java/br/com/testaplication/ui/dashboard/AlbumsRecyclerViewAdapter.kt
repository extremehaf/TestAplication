package br.com.testaplication.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.testaplication.data.model.Album
import br.com.testaplication.databinding.ItemAlbumBinding


class AlbumsRecyclerViewAdapter(var album: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumsViewHolder>() {

    fun updateLocations(newAlbums: List<Album>) {
        album.clear()
        album.addAll(newAlbums)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = AlbumsViewHolder(
        ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = album.size
    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val location: Album = album[position]

        holder.mIsInTheMiddle = position == (album.size / 2)
        holder.bind(location)
    }
}

class AlbumsViewHolder(val binding: ItemAlbumBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // We'll use this field to showcase matching the holder from the test.
    var mIsInTheMiddle = false

    fun bind(album: Album) {
        binding.album = album
        binding.executePendingBindings()
    }
}