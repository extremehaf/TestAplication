package br.com.testaplication.data.repository.posts

import androidx.lifecycle.LiveData
import br.com.testaplication.data.model.Post
import br.com.testaplication.data.model.Todo
import io.realm.RealmResults
import io.realm.Sort

interface PostRepository {
    fun findAll(): LiveData<List<Post>>
    fun findAllSorted(sortField: String?, sort: Sort, detached: Boolean): List<Post>
    fun save(post: Post)
    fun delete(post: Post)
}