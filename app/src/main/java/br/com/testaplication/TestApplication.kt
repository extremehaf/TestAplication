package br.com.testaplication

import android.util.Log
import br.com.testaplication.data.model.Album
import br.com.testaplication.data.model.Post
import br.com.testaplication.data.model.Todo
import br.com.testaplication.data.network.ApiService
import br.com.testaplication.di.components.AppComponent
import br.com.testaplication.di.components.DaggerAppComponent
import dagger.android.DaggerApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("unused")
class TestApplication : DaggerApplication() {
    var appComponent: AppComponent = DaggerAppComponent.factory().create(this)

    private val disposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()



        Realm.init(this)
        var realm = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realm)

        loadData()
    }

    private fun configureService(): ApiService {
        var build = Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

        return build.create(ApiService::class.java)
    }

    private fun loadData() {

        var realm = Realm.getDefaultInstance()
        loadAlbuns(realm)
        loadPosts(realm)
        loadTodo(realm)
    }

    private fun loadPosts(realm: Realm) {
        configureService().getPosts()
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<List<Post>>() {
                override fun onSuccess(data: List<Post>) {
                    Log.d("sucesso ", "" + data)
                    realm.executeTransactionAsync {
                        it.insertOrUpdate(data)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("error ", "" + e.printStackTrace())
                }
            })?.let {
                disposable.add(
                    it
                )
            }
    }
    private fun loadAlbuns(realm: Realm) {
        configureService().getAlbums()
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<List<Album>>() {
                override fun onSuccess(data: List<Album>) {
                    Log.d("sucesso ", "" + data)
                    realm.executeTransactionAsync {
                        it.insertOrUpdate(data)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("error ", "" + e.printStackTrace())
                }
            })?.let {
                disposable.add(
                    it
                )
            }
    }
    private fun loadTodo(realm: Realm) {
        configureService().getTodos()
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<List<Todo>>() {
                override fun onSuccess(data: List<Todo>) {
                    Log.d("sucesso ", "" + data)
                    realm.executeTransactionAsync {
                        it.insertOrUpdate(data)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("error ", "" + e.printStackTrace())
                }
            })?.let {
                disposable.add(
                    it
                )
            }
    }

    override fun applicationInjector() = appComponent
}