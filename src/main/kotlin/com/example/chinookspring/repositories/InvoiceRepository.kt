package repositories

import entities.Artist
import entities.Entity
import entities.Invoice
import entities.SingleKeyEntity
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.LocalDate
import java.time.LocalDateTime

class InvoiceRepository: SingleKeyEntityRepository<Invoice, Int>() {
    override val entityName: String
        get() = "Invoice"
    override val keyNames: String
        get() = "InvoiceId"

    override fun readEntity(result: ResultSet): Invoice {
        val entity = Invoice()
        entity.invoicId = result.getInt(1)
        entity.customerId = result.getInt(2)
        entity.invoiceDate = LocalDateTime.parse(result.getString(3))
        entity.billingAddress = result.getString(4)
        entity.billingCity = result.getString(5)
        entity.billingState = result.getString(6)
        entity.billingCountry = result.getString(7)
        entity.billingPostalCode = result.getString(8)
        entity.total = result.getInt(9)

        return entity
    }

    fun find(name: String): MutableList<Invoice> {
        val statement = createStatement(
                "select * from Invoice where BillingCity like ?")
        statement.setString(1, "%$name%")

        val result = statement.executeQuery()

        val invoices = mutableListOf<Invoice>()
        while (result.next()) {
            val invoice = readEntity(result)

            invoices.add(invoice)
        }

        close(statement)

        return invoices
    }


    override fun insertCore(entity: Invoice): PreparedStatement {
        val statement = createStatement(
                "insert into Invoice values(?, ?, ?, ?, ?, ?, ?, ?)")
        statement.setInt(1, entity.customerId)
        statement.setString(2, entity.invoiceDate.toString().replace("T", " ").dropLast(6))
        statement.setString(3, entity.billingAddress)
        statement.setString(4, entity.billingCity)
        statement.setString(5, entity.billingState)
        statement.setString(6, entity.billingCountry)
        statement.setString(7, entity.billingPostalCode)
        statement.setInt(8, entity.total)

        return statement
    }

    override fun updateCore(entity: Invoice): PreparedStatement {
        val statement = createStatement(
                "update Invoice set BillingCity = ? where InvoiceId = ?")
        statement.setString(1, entity.billingCity)
        statement.setInt(2, entity.invoicId)

        return statement
    }
}