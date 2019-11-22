package com.example.chinookspring.repositories

import com.example.chinookspring.entities.Todo
import com.example.chinookspring.repositories.Repository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

internal class TodoRepositoryTest {
    @Test
    fun count() {
        val count = Repository.todo.count()
        assertTrue(count > 0)
    }

    @Test
    fun getAll() {
        val todos = Repository.todo.getAll()
        assertTrue(todos.size > 0)
    }

    @Test
    fun getById() {
        val todo = Repository.todo.getByPK(1)
        assertEquals("go to school", todo!!.title)
    }

    @Test
    fun find() {
        val todos = Repository.todo.find("go to")
        assertTrue(todos.size > 0)
    }

    @Test
    fun insert(){
        val oldCount = Repository.todo.count()

        val todo = Todo()
        todo.title = LocalDateTime.now().toString()

        Repository.todo.insert(todo)

        val newCount = Repository.todo.count()
        assertEquals(oldCount + 1, newCount)
    }

    @Test
    fun  update(){
        val todoId = 2
        val title = LocalDateTime.now().toString()

        val todo = Todo()
        todo.todoId = todoId
        todo.title = title

        Repository.todo.update(todo)

        val updatedTodo = Repository.todo.getByPK(todoId)
        assertEquals(title, updatedTodo!!.title)
    }

    @Test
    fun delete(){
        val oldCount = Repository.todo.count()
        val lastTodo = Repository.todo.getLast()!!

        Repository.todo.deleteByPK(lastTodo.todoId)
        val newCount = Repository.todo.count()

        assertEquals(oldCount -1, newCount)
    }

    @Test
    fun getLast(){
        val lastTodo = Repository.todo.getLast()
        val todos = Repository.todo.getAll()

        assertEquals(todos[todos.size - 1].todoId, lastTodo!!.todoId)
    }
}