package br.com.testaplication.data.repository.todos

import androidx.lifecycle.LiveData
import br.com.testaplication.data.asLiveData
import br.com.testaplication.data.model.Todo
import br.com.testaplication.data.repository.RealmResultsLiveData
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import javax.inject.Inject
import javax.inject.Provider


class RealmTodosRepository
@Inject
constructor(private val realmProvider: Provider<Realm>) : TodoRepository {


    override fun findAll(): LiveData<List<Todo>> {
        realmProvider.get().use { realm ->
            val realmResults = realm.where(Todo::class.java).findAll()
            return realmResults.asLiveData()

        }
    }

    override fun findAllSorted(sortField: String?, sort: Sort, detached: Boolean): List<Todo> {
        realmProvider.get().use { realm ->
            val realmResults = realm.where(Todo::class.java).findAllSorted(sortField, sort)

            if (detached) {
                return realm.copyFromRealm(realmResults)
            } else {
                return realmResults
            }
        }
    }

    override fun save(post: Todo) {
        realmProvider.get().use { realm ->
            realm.executeTransaction { r -> r.copyToRealmOrUpdate(post) }
        }
    }

    override fun delete(realmTodo: Todo) {
        if (realmTodo.isValid) {
            realmProvider.get().use { realm ->

                realm.executeTransaction { r ->
                    realmTodo.deleteFromRealm()
                }

            }
        }
    }
}