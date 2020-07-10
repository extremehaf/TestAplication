package br.com.testaplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.testaplication.data.model.Post
import br.com.testaplication.data.network.ApiService
import br.com.testaplication.data.repository.posts.PostRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    var apiService: ApiService,
    var postRepository: PostRepository
) : ViewModel() {

    val posts: LiveData<List<Post>>
    val postsCount: LiveData<Int>
    val locationsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        posts = Transformations.map(postRepository.findAll()) {
                input ->
            input
        }

        postsCount = Transformations.map(posts) {
                input ->
            input.size
        }
        locationsLoadError.value = false
        loading.value = false
    }
}