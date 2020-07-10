package br.com.testaplication.di.modules


import br.com.testaplication.di.compose.ViewModelActivity
import br.com.testaplication.di.compose.ViewModelFragment
import br.com.testaplication.di.scopes.ActivityScope
import br.com.testaplication.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.realm.Realm

@Module
abstract class ComposeModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeViewModelActivity(): ViewModelActivity

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeViewModelFragment(): ViewModelFragment
}