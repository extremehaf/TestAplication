package br.com.testaplication.di.modules

import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class AppModule {

    @Provides
    internal fun provideRealm(): Realm = Realm.getDefaultInstance()
}