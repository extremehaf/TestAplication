package br.com.testaplication.data.repository.todos

import androidx.lifecycle.LiveData
import br.com.testaplication.data.model.Post
import br.com.testaplication.data.model.Todo
import io.realm.RealmResults
import io.realm.Sort

interface TodoRepository {

    fun findAll(): LiveData<List<Todo>>
    fun findAllSorted(sortField: String?, sort: Sort, detached: Boolean): List<Todo>
    fun save(todo: Todo)
    fun delete(todo: Todo)
}