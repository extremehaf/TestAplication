package br.com.testaplication.data.repository.albums

import androidx.lifecycle.LiveData
import br.com.testaplication.data.model.Album
import br.com.testaplication.data.model.Post
import io.realm.Sort

interface AlbumsRepository {
    fun findAll(): LiveData<List<Album>>
    fun findAllSorted(sortField: String?, sort: Sort, detached: Boolean): List<Album>

    fun save(album: Album)
    fun delete(album: Album)
}