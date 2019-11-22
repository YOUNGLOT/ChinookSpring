package com.example.chinookspring.repositories

import com.example.chinookspring.entities.Album
import com.example.chinookspring.repositories.Repository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

internal class AlbumRepositoryTest {
    @Test
    fun count() {
        val count = Repository.album.count()
        assertTrue(count > 0)
    }

    @Test
    fun getAll() {
        val albums = Repository.album.getAll()
        assertTrue(albums.size > 0)
    }

    @Test
    fun getById() {
        val album = Repository.album.getByPK(1)
        assertEquals("For Those About To Rock We Salute You", album!!.title)
    }

    @Test
    fun find() {
        val albums = Repository.album.find("rock")
        assertEquals(7, albums.size)
    }

    @Test
    fun insert(){
        val oldCount = Repository.album.count()

        val album = Album()
        album.title = LocalDateTime.now().toString()
        album.artistId = 1

        Repository.album.insert(album)

        val newCount = Repository.album.count()
        assertEquals(oldCount + 1, newCount)
    }

    @Test
    fun  update(){
        val albumId = 2
        val title = LocalDateTime.now().toString()

        val album = Album()
        album.albumId = albumId
        album.title = title
        album.artistId = 1

        Repository.album.update(album)

        val updatedAlbum = Repository.album.getByPK(albumId)
        assertEquals(title, updatedAlbum!!.title)
    }

    @Test
    fun delete(){
        val oldCount = Repository.album.count()
        val lastAlbum = Repository.album.getLast()!!

        Repository.album.deleteByPK(lastAlbum.albumId)
        val newCount = Repository.album.count()

        assertEquals(oldCount -1, newCount)
    }

    @Test
    fun getLast(){
        val lastAlbum = Repository.album.getLast()
        val albums = Repository.album.getAll()

        assertEquals(albums[albums.size - 1].albumId, lastAlbum!!.albumId)
    }
}