package repositories

import com.example.chinookspring.entities.Album
import java.sql.PreparedStatement
import java.sql.ResultSet

class AlbumRepository : SingleKeyEntityRepository<Album, Int>(){
    override val entityName get() = "Album"
    override val keyNames get() = "AlbumId"

    override fun readEntity(result: ResultSet): Album {
        val album = Album()
        album.albumId = result.getInt(1)
        album.title = result.getString(2)
        album.artistId = result.getInt(3)

        return album
    }

    fun find(title: String): MutableList<Album> {
        val statement = createStatement("select AlbumId, Title, ArtistId from Album where Title like ?")
        statement.setString(1, "%$title%")

        val result = statement.executeQuery()

        val albums = mutableListOf<Album>()
        while (result.next()) {
            val album = readEntity(result)

            albums.add(album)
        }

        close(statement)

        return albums
    }

    override fun insertCore(entity: Album): PreparedStatement {
        val statement = createStatement("insert into Album values(?, ?)")
        statement.setString(1, entity.title)
        statement.setInt(2, entity.artistId)

        return statement
    }

    override fun updateCore(entity: Album): PreparedStatement {
        val statement = createStatement("update Album set Title = ?, ArtistId = ? where AlbumId = ?")
        statement.setString(1, entity.title)
        statement.setInt(2, entity.artistId)
        statement.setInt(3, entity.albumId)

        return statement
    }

//    override val getLastQuery = "select top 1 * from Album order by Title asc"
}