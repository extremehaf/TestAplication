package br.com.testaplication.data.model

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey


open class Album : RealmObject() {
    @PrimaryKey
    open var id: Int? = null
    open var title: String? = null
    open var userId: Int? = null


}