package com.example.chinookspring.repositories

import com.example.chinookspring.entities.Person
import java.sql.PreparedStatement
import java.sql.ResultSet

class PersonRepository: SingleKeyEntityRepository<Person, String>() {
    override val entityName: String
        get() = "Person"
    override val keyNames: String
        get() = "Nid"

    override fun readEntity(result: ResultSet): Person {
        val entity = Person()
        entity.nId = result.getString(1)
        entity.name = result.getString(2)

        return entity
    }

    fun find(name: String): MutableList<Person> {
        val statement = createStatement(
                "select * from Person where Name like ?")
        statement.setString(1, "%$name%")

        val result = statement.executeQuery()

        val persons = mutableListOf<Person>()
        while (result.next()) {
            val person = readEntity(result)

            persons.add(person)
        }

        close(statement)

        return persons
    }


    override fun insertCore(entity: Person): PreparedStatement {
        val statement = createStatement(
                "insert into Person values(?, ?)")
        statement.setString(1, entity.nId)
        statement.setString(2, entity.name)

        return statement
    }

    override fun updateCore(entity: Person): PreparedStatement {
        val statement = createStatement(
                "update Person set Name = ? where nId = ?")
        statement.setString(1, entity.name)
        statement.setString(2, entity.nId)

        return statement
    }
}