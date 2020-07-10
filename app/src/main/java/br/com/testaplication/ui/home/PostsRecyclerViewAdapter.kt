package br.com.testaplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.testaplication.data.model.Post
import br.com.testaplication.databinding.ItemPostBinding


class PostsRecyclerViewAdapter(var posts: ArrayList<Post>) :
    RecyclerView.Adapter<PostsViewHolder>() {

    fun updateLocations(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = PostsViewHolder(
        ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = posts.size
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val location: Post = posts[position]

        holder.mIsInTheMiddle = position == (posts.size / 2)
        holder.bind(location)
    }
}

class PostsViewHolder(val binding: ItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // We'll use this field to showcase matching the holder from the test.
    var mIsInTheMiddle = false

    fun bind(post: Post) {
        binding.post = post
        binding.executePendingBindings()
    }
}