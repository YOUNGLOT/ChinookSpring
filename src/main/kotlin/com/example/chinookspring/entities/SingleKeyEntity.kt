package com.example.chinookspring.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

//@JsonIgnoreProperties("keyValue1")
abstract class SingleKeyEntity<K1> : Entity(){
    @get:JsonIgnore
    abstract val keyValue1: K1
}