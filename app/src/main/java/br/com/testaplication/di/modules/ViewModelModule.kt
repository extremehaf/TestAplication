package br.com.testaplication.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.testaplication.di.qualifier.ViewModelKey
import br.com.testaplication.ui.dashboard.AlbumsViewModel
import br.com.testaplication.ui.home.HomeViewModel
import br.com.testaplication.ui.Todos.TodosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(mainActivityViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    internal abstract fun bindDashboardViewModelModel(movieDetailViewModel: AlbumsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TodosViewModel::class)
    internal abstract fun bindNotificationsViewModel(tvDetailViewModel: TodosViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}