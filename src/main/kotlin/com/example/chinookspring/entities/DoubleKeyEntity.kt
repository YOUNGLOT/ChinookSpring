package entities

abstract class DoubleKeyEntity<K1, K2> : SingleKeyEntity<K1>(){
    abstract val keyValue2: K2
}

abstract class TripleKeyEntity<K1, K2, K3> : DoubleKeyEntity<K1, K2>(){
    abstract val keyValue3: K3
}
