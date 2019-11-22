package entities

class Artist : SingleKeyEntity<Int>() {
    var artistId: Int = 0
    var name: String = ""

    override val keyValue1: Int
        get() = artistId
}