package br.com.testaplication.di.modules

import br.com.testaplication.di.scopes.FragmentScope
import br.com.testaplication.ui.dashboard.AlbumsFragment
import br.com.testaplication.ui.home.HomeFragment
import br.com.testaplication.ui.Todos.TodosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeTvListFragment(): AlbumsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributePersonListFragment(): TodosFragment
}