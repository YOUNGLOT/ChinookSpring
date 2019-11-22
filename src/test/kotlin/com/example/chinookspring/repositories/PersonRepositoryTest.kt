package repositories

import entities.Person
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PersonRepositoryTest {
    @Test
    fun count() {

        val count = Repository.person.count()
        Assertions.assertTrue(count > 0)
    }

    @Test
    fun getAll() {
        val persons = Repository.person.getAll()
        Assertions.assertTrue(persons.size > 0)
    }

    @Test
    fun getById() {
        val person = Repository.person.getByPK("b4")
        Assertions.assertEquals("ringo", person!!.name)
    }

    @Test
    fun find() {
        val persons = Repository.person.find("a")
        Assertions.assertEquals(2, persons.size)
    }

    @Test
    fun insert() {
        val oldCount = Repository.person.count()

        val person = Person()
        person.nId = "z10"
        person.name = "김경태"

        Repository.person.insert(person)

        val newCount = Repository.person.count()
        Assertions.assertEquals(oldCount + 1, newCount)

        Repository.person.delete(person)
    }

    @Test
    fun update() {
        val testName = "김경태"
        val person = Repository.person.getByPK("b1")!!
        val nameSource = person.name
        person.name = testName

        Repository.person.update(person)

        val updatedPerson = Repository.person.getByPK(person.nId)
        Assertions.assertEquals(testName, updatedPerson!!.name)

        person.name = nameSource
        Repository.person.update(person)
    }

    @Test
    fun delete() {
        var person = Person()
        person.nId = "z10"
        person.name = "김경태"

        Repository.person.insert(person)

        val oldCount = Repository.person.count()
        Repository.person.delete(person)
        val newCount = Repository.person.count()
        Assertions.assertEquals(oldCount - 1, newCount)
    }

    @Test
    fun getLast() {
        val lastPerson = Repository.person.getLast()
        val persons = Repository.person.getAll()
        Assertions.assertEquals(persons[persons.size - 1].nId, lastPerson!!.nId)
    }
}