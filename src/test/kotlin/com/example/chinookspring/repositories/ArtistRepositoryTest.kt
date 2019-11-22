package com.example.chinookspring.repositories

import com.example.chinookspring.entities.Artist
import com.example.chinookspring.repositories.Repository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

internal class ArtistRepositoryTest {
    @Test
    fun count() {
        val count = Repository.artist.count()
        assertTrue(count > 0)
    }

    @Test
    fun getAll() {
        val artists = Repository.artist.getAll()
        assertTrue(artists.size > 0)
    }

    @Test
    fun getById() {
        val artist = Repository.artist.getByPK(1)
        assertEquals("AC/DC", artist!!.name)
    }

    @Test
    fun find() {
        val artists = Repository.artist.find("chico")
        assertEquals(2, artists.size)
    }

    @Test
    fun insert(){
        val oldCount = Repository.artist.count()

        val artist = Artist()
        artist.name = LocalDateTime.now().toString()

        Repository.artist.insert(artist)

        val newCount = Repository.artist.count()
        assertEquals(oldCount + 1, newCount)
    }

    @Test
    fun  update(){
        val artistId = 2
        val name = LocalDateTime.now().toString()

        val artist = Artist()
        artist.artistId = artistId
        artist.name = name

        Repository.artist.update(artist)

        val updatedArtist = Repository.artist.getByPK(artistId)
        assertEquals(name, updatedArtist!!.name)
    }

    @Test
    fun delete(){
        val oldCount = Repository.artist.count()
        val lastArtist = Repository.artist.getLast()!!

        Repository.artist.deleteByPK(lastArtist.artistId)
        val newCount = Repository.artist.count()

        assertEquals(oldCount -1, newCount)
    }

    @Test
    fun getLast(){
        val lastArtist = Repository.artist.getLast()
        val artists = Repository.artist.getAll()

        assertEquals(artists[artists.size - 1].artistId, lastArtist!!.artistId)
    }
}