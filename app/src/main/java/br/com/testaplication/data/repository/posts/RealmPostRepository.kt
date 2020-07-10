package br.com.testaplication.data.repository.posts

import androidx.lifecycle.LiveData
import br.com.testaplication.data.asLiveData
import br.com.testaplication.data.model.Post
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import javax.inject.Inject
import javax.inject.Provider

class RealmPostRepository
@Inject
constructor(private val realmProvider: Provider<Realm>) : PostRepository {

    override fun findAll(): LiveData<List<Post>> {
        realmProvider.get().use { realm ->
            val realmResults = realm.where(Post::class.java).findAll()
            return realmResults.asLiveData()

        }
    }
    override fun findAllSorted(sortField: String?, sort: Sort, detached: Boolean): List<Post> {
        realmProvider.get().use { realm ->
            val realmResults = realm.where(Post::class.java).findAllSorted(sortField, sort)

            if (detached) {
                return realm.copyFromRealm(realmResults)
            } else {
                return realmResults
            }
        }
    }

    override fun save(post: Post) {
        realmProvider.get().use { realm ->
            realm.executeTransaction { r -> r.copyToRealmOrUpdate(post) }
        }
    }

    override fun delete(realmPost: Post) {
        if (realmPost.isValid) {
            realmProvider.get().use { realm ->

                realm.executeTransaction { r ->
                    realmPost.deleteFromRealm()
                }

            }
        }
    }
}