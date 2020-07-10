package br.com.testaplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.testaplication.data.model.Album
import br.com.testaplication.data.network.ApiService
import br.com.testaplication.data.repository.albums.AlbumsRepository
import io.realm.Sort
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
    var apiService: ApiService,
    var albumRepository: AlbumsRepository
) : ViewModel() {

    var albums = MutableLiveData<List<Album>>()
    var albumsCount: LiveData<Int>
    var locationsLoadError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()

    init {
        var a = albumRepository.findAll()
        var b = albumRepository.findAllSorted("id", sort = Sort.ASCENDING, detached = false)
        albums.value = b

//        albums.value = Transformations.map(albumRepository.findAll()) {
//                input ->
//            input
//        }.value

        albumsCount = Transformations.map(albums) {
                input ->
            input.size
        }
        locationsLoadError.value = false
        loading.value = false
    }
}