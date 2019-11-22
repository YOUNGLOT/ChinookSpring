package com.example.chinookspring.entities

import com.example.chinookspring.entities.SingleKeyEntity
import java.sql.Date
import java.time.LocalDate

class Todo : SingleKeyEntity<Int>() {
    var todoId: Int = 0
    var title: String = ""
    var date: Date = Date.valueOf(LocalDate.now())

    override val keyValue1: Int
        get() = todoId
}