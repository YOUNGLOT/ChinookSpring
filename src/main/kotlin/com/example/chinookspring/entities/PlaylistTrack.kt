package entities

class PlaylistTrack: DoubleKeyEntity<Int, Int>() {
    var playlistId: Int = 0
    var trackId: Int = 0

    override val keyValue1: Int
        get() = playlistId

    override val keyValue2: Int
        get() = trackId
}