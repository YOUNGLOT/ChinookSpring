package entities

class Person: SingleKeyEntity<String>() {
    var nId: String = ""
    var name: String = ""

    override val keyValue1: String
        get() = nId
}