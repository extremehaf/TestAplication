package br.com.testaplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.testaplication.R
import br.com.testaplication.data.model.Album
import br.com.testaplication.data.model.Post
import br.com.testaplication.di.compose.ViewModelFragment
import br.com.testaplication.ui.home.HomeViewModel
import br.com.testaplication.ui.home.PostsRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_albums.*
import kotlinx.android.synthetic.main.fragment_albums.list_error
import kotlinx.android.synthetic.main.fragment_albums.loading_view

class AlbumsFragment : ViewModelFragment() {


    private val viewModel: AlbumsViewModel by injectActivityVIewModels()
    private val postsAdapter = AlbumsRecyclerViewAdapter(arrayListOf<Album>())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_albums, container, false)

        val locationsList: RecyclerView = root.findViewById(R.id.albumsList)
        val layout = LinearLayoutManager(this.context)
        locationsList.apply {
            layoutManager = layout
            adapter = postsAdapter
        }
        observeViewModel()
        return root
    }

    private fun observeViewModel() {
        viewModel.albums.observe(viewLifecycleOwner, Observer { albums ->
            albums?.let {
                albumsList.visibility = View.VISIBLE
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
                    albumsList.visibility = View.GONE
                }
            }
        })
    }
}