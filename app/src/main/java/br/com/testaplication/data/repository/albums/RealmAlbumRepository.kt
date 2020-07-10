package br.com.testaplication.data.repository.albums

import androidx.lifecycle.LiveData
import br.com.testaplication.data.asLiveData
import br.com.testaplication.data.model.Album
import br.com.testaplication.data.model.Post
import io.realm.Realm
import io.realm.Sort
import javax.inject.Inject
import javax.inject.Provider

class RealmAlbumRepository
@Inject
constructor(private val realmProvider: Provider<Realm>) : AlbumsRepository {

    override fun findAll(): LiveData<List<Album>> {
        realmProvider.get().use { realm ->
            val realmResults = realm.where(Album::class.java).findAll()
            return realmResults.asLiveData()

        }
    }
    override fun findAllSorted(sortField: String?, sort: Sort, detached: Boolean): List<Album> {
        realmProvider.get().use { realm ->
            val realmResults = realm.where(Album::class.java).findAllSorted(sortField, sort)

            if (detached) {
                return realm.copyFromRealm(realmResults)
            } else {
                return realmResults
            }
        }
    }

    override fun save(album: Album) {
        realmProvider.get().use { realm ->
            realm.executeTransaction { r -> r.copyToRealmOrUpdate(album) }
        }
    }

    override fun delete(realmAlbum: Album) {
        if (realmAlbum.isValid) {
            realmProvider.get().use { realm ->

                realm.executeTransaction { r ->
                    realmAlbum.deleteFromRealm()
                }

            }
        }
    }
}