package br.com.testaplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.testaplication.R
import br.com.testaplication.data.model.Post
import br.com.testaplication.di.compose.ViewModelFragment
import kotlinx.android.synthetic.main.fragment_posts.*


class HomeFragment : ViewModelFragment() {


    private val viewModel: HomeViewModel by injectActivityVIewModels()
    private val postsAdapter = PostsRecyclerViewAdapter(arrayListOf<Post>())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_posts, container, false)

        val locationsList: RecyclerView = root.findViewById(R.id.postsList)
        val layout = LinearLayoutManager(this.context)
        locationsList.apply {
            layoutManager = layout
            adapter = postsAdapter
        }
        observeViewModel()
        return root
    }

    private fun observeViewModel() {
        viewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            posts?.let {
                postsList.visibility = View.VISIBLE
                postsAdapter.updateLocations(it)
            }
        })
        viewModel.locationsLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                list_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    postsList.visibility = View.GONE
                }
            }
        })
    }
}
