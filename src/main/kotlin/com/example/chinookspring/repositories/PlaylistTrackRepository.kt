package repositories

import entities.PlaylistTrack
import exceptions.UnupdatableException
import java.lang.Exception
import java.sql.PreparedStatement
import java.sql.ResultSet

class PlaylistTrackRepository: DoubleKeyEntityRepository<PlaylistTrack, Int, Int>() {
    override val entityName: String
        get() = "PlaylistTrack"
    override val keyNames: String
        get() = "playlistId, trackId"

    override fun readEntity(result: ResultSet): PlaylistTrack {
        val entity = PlaylistTrack()
        entity.playlistId = result.getInt(1)
        entity.trackId = result.getInt(2)

        return entity
    }

    fun find(playlistId: Int): MutableList<PlaylistTrack> {
        val statement = createStatement(
                "select * from PlaylistTrack where PlaylistId = ?")
        statement.setInt(1, playlistId)

        val result = statement.executeQuery()

        val playlistTracks = mutableListOf<PlaylistTrack>()
        while (result.next()) {
            val playlistTrack = readEntity(result)

            playlistTracks.add(playlistTrack)
        }

        close(statement)

        return playlistTracks
    }

    override fun insertCore(entity: PlaylistTrack): PreparedStatement {
        val statement = createStatement(
                "insert into PlaylistTrack values(?, ?)")
        statement.setInt(1, entity.playlistId)
        statement.setInt(2, entity.trackId)

        return statement
    }

    override fun updateCore(entity: PlaylistTrack): PreparedStatement {
        throw UnupdatableException(2, "기본키만 있는 엔터티는 업데이트 할 수 없음")
    }
}