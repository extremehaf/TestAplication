package br.com.testaplication.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Todo : RealmObject() {
    @PrimaryKey
    open var id: Int? = null
    open var title: String? = null
    open var completed: Boolean? = null
    open var userId: Int? = null
}