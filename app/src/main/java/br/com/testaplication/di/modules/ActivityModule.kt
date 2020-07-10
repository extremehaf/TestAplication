package br.com.testaplication.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

import androidx.navigation.ActivityNavigator
import androidx.navigation.Navigator
import br.com.testaplication.MainActivity
import br.com.testaplication.di.scopes.ActivityScope

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.realm.Realm

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}