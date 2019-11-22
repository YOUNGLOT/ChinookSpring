package repositories

import entities.Todo
import java.sql.PreparedStatement
import java.sql.ResultSet

class TodoRepository : SingleKeyEntityRepository<Todo, Int>(){
    override val entityName get() = "Todo"
    override val keyNames get() = "TodoId"

    override fun readEntity(result: ResultSet): Todo {
        val todo = Todo()
        todo.todoId = result.getInt(1)
        todo.title = result.getString(2)
        todo.date = result.getDate(3)

        return todo
    }

    fun find(title: String): MutableList<Todo> {
        val statement = createStatement("select * from Todo where Title like ?")
        statement.setString(1, "%$title%")

        val result = statement.executeQuery()

        val todos = mutableListOf<Todo>()
        while (result.next()) {
            val todo = readEntity(result)

            todos.add(todo)
        }

        close(statement)

        return todos
    }

    override fun insertCore(entity: Todo): PreparedStatement {
        val statement = createStatement("insert into Todo values(?, ?)")
        statement.setString(1, entity.title)
        statement.setDate(2, entity.date)

        return statement
    }

    override fun updateCore(entity: Todo): PreparedStatement {
        val statement = createStatement("update Todo set Title = ?, Date = ? where TodoId = ?")
        statement.setString(1, entity.title)
        statement.setDate(2, entity.date)
        statement.setInt(3, entity.todoId)

        return statement
    }
}