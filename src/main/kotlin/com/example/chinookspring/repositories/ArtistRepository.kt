package repositories

import entities.Artist
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class ArtistRepository : SingleKeyEntityRepository<Artist, Int>() {
    override val entityName get() = "Artist"

    override val keyNames get() = "ArtistId"

    override fun readEntity(result: ResultSet): Artist {
        val artist = Artist()
        artist.artistId = result.getInt(1)
        artist.name = result.getString(2)

        return artist
    }

    fun find(name: String): MutableList<Artist> {
        val statement = createStatement("select ArtistId, Name from Artist where Name like ?")
        statement.setString(1, "%$name%")

        val result = statement.executeQuery()

        val artists = mutableListOf<Artist>()
        while (result.next()) {
            val artist = readEntity(result)

            artists.add(artist)
        }

        close(statement)

        return artists
    }

    override fun insertCore(entity: Artist): PreparedStatement {
        val statement = createStatement("insert into Artist values(?)")
        statement.setString(1, entity.name)

        return statement
    }

    override fun updateCore(entity: Artist): PreparedStatement {
        val statement = createStatement("update Artist set Name = ? where ArtistId = ?")
        statement.setString(1, entity.name)
        statement.setInt(2, entity.artistId)

        return statement
    }
}
