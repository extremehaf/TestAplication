package br.com.testaplication.data.network

import br.com.testaplication.data.model.Album
import br.com.testaplication.data.model.Post
import br.com.testaplication.data.model.Todo
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    fun getPosts(): Single<List<Post>>
    @GET("/albums")
    fun getAlbums():  Single<List<Album>>
    @GET("/todos")
    fun getTodos(): Single<List<Todo>>
}