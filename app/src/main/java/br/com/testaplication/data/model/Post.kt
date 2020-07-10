package br.com.testaplication.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Post : RealmObject() {

    @PrimaryKey
    open var id: Int? = null
    open var title: String? = null
    open var body: String? = null
    open var userId: Int? = null
}