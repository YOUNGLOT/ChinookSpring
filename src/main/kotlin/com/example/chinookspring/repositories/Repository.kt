package com.example.chinookspring.repositories

object Repository {
    val album = AlbumRepository()

    val artist = ArtistRepository()
    val invoice = InvoiceRepository()
    val person = PersonRepository()
    val playlistTrack = PlaylistTrackRepository()
    val todo = TodoRepository()
}