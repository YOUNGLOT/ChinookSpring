package entities

abstract class SingleKeyEntity<K1> : Entity(){
    abstract val keyValue1: K1
}