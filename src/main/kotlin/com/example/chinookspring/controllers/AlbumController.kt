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
}