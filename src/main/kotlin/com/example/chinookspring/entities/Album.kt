package com.example.chinookspring.entities

import entities.SingleKeyEntity

// Value Object == Entity == Data Transfer Object

class Album : SingleKeyEntity<Int>(){
    var albumId: Int = 0
    var title: String = ""
    var artistId: Int = 0

    override fun toString() = "$albumId / $title / $artistId"

    override val keyValue1: Int
        get() = albumId
}
