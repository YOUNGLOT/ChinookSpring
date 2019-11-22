package com.example.chinookspring.repositories

import com.example.chinookspring.entities.PlaylistTrack
import com.example.chinookspring.exceptions.UnupdatableException
import com.example.chinookspring.repositories.Repository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlaylistTrackRepositoryTest {
    @Test
    fun count() {
        val count = Repository.playlistTrack.count()
        Assertions.assertTrue(count > 0)
    }

    @Test
    fun getAll() {
        val playlistTracks = Repository.playlistTrack.getAll()
        Assertions.assertTrue(playlistTracks.size > 0)
    }

    @Test
    fun getById() {
        val playlistTrack = Repository.playlistTrack.getByPK(1, 647)
        Assertions.assertEquals(1, playlistTrack!!.playlistId)
        Assertions.assertEquals(647, playlistTrack.trackId)
    }

    @Test
    fun find() {
        val playlistTrack = Repository.playlistTrack.find(1)
        Assertions.assertEquals(3290, playlistTrack.size)
    }

    @Test
    fun insert() {
        val oldCount = Repository.playlistTrack.count()

        val playlistTrack = PlaylistTrack()
        playlistTrack.playlistId = 2
        playlistTrack.trackId = 3000

        Repository.playlistTrack.insert(playlistTrack)

        val newCount = Repository.playlistTrack.count()
        Assertions.assertEquals(oldCount + 1, newCount)

        Repository.playlistTrack.delete(playlistTrack)
    }

    @Test
    fun update() {
        assertThrows<UnupdatableException> {
            Repository.playlistTrack.update(PlaylistTrack())
        }
    }

    @Test
    fun delete() {
        val playlistTrack = PlaylistTrack()
        playlistTrack.playlistId = 2
        playlistTrack.trackId = 3000

        Repository.playlistTrack.insert(playlistTrack)

        val oldCount = Repository.playlistTrack.count()
        Repository.playlistTrack.delete(playlistTrack)
        val newCount = Repository.playlistTrack.count()
        Assertions.assertEquals(oldCount - 1, newCount)
    }

    @Test
    fun getLast() {
        val lastPlaylistTrack = Repository.playlistTrack.getLast()
        val playlistTracks = Repository.playlistTrack.getAll()
        Assertions.assertEquals(playlistTracks[playlistTracks.size - 1].trackId, lastPlaylistTrack!!.trackId)
    }
}