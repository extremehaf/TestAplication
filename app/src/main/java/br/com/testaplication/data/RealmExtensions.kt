package br.com.testaplication.data

import br.com.testaplication.data.repository.RealmLiveData
import br.com.testaplication.data.repository.RealmResultsLiveData
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults


// Add asLiveData extension to RealmResults
fun <E : RealmModel> RealmResults<E>.asLiveData() = RealmResultsLiveData(this)

// Add asLiveData extension to RealmModels
fun <E : RealmModel> E.asLiveData() = RealmLiveData(this)