package br.com.testaplication.di.modules

import br.com.testaplication.data.model.Post
import br.com.testaplication.data.repository.albums.AlbumsRepository
import br.com.testaplication.data.repository.albums.RealmAlbumRepository
import br.com.testaplication.data.repository.posts.PostRepository
import br.com.testaplication.data.repository.posts.RealmPostRepository
import br.com.testaplication.data.repository.todos.RealmTodosRepository
import br.com.testaplication.data.repository.todos.TodoRepository
import dagger.Binds
import dagger.Module


@Module
abstract class PersistenceModule {

    @Binds
    abstract fun bindPostRepo(realmCountryRepo: RealmPostRepository): PostRepository

    @Binds
    abstract fun bindAlbumRepo(realmCountryRepo: RealmAlbumRepository): AlbumsRepository
    @Binds
    abstract fun bindTodoRepo(realmCountryRepo: RealmTodosRepository): TodoRepository




}