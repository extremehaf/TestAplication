package br.com.testaplication.di.components

import android.app.Application
import br.com.testaplication.di.modules.*
import br.com.testaplication.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ComposeModule::class,
    ActivityModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    PersistenceModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}