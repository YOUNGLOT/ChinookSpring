package com.example.chinookspring.controllers

import com.example.chinookspring.entities.Album
import com.example.chinookspring.repositories.Repository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/album")  // routing
class AlbumController {
    @GetMapping
    fun getAll() = Repository.album.getAll()

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET])
    fun getById(@PathVariable id: Int) = Repository.album.getByPK(id)

    @PostMapping
    fun insert(@RequestBody album: Album){
        Repository.album.insert(album)
    }

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.PUT])
    fun update(@RequestBody album: Album, @PathVariable id: Int){
        val entity = Repository.album.getByPK(id)
        if (entity == null)
            return

        entity.title = album.title
        entity.artistId = album.artistId
        Repository.album.update(entity)
    }

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteByPK(@PathVariable id: Int) = Repository.album.deleteByPK(id)

}