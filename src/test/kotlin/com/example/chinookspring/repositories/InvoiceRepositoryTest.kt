package com.example.chinookspring.repositories

import com.example.chinookspring.entities.Invoice
import com.example.chinookspring.repositories.Repository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class InvoiceRepositoryTest {
    @Test
    fun count() {
        val count = Repository.invoice.count()
        Assertions.assertTrue(count > 0)
    }

    @Test
    fun getAll() {
        val invoices = Repository.invoice.getAll()
        Assertions.assertTrue(invoices.size > 0)
    }

    @Test
    fun getById() {
        val invoice = Repository.invoice.getByPK(1)
        Assertions.assertEquals("Theodor-Heuss-Straße 34", invoice!!.billingAddress)
    }

    @Test
    fun find() {
        val invoices = Repository.invoice.find("Amsterdam")
        Assertions.assertEquals(7, invoices.size)
    }

    @Test
    fun insert() {
        val oldCount = Repository.invoice.count()

        val invoice = Invoice()
        invoice.billingCity = "김경태 도시"
        invoice.customerId = 3
        invoice.invoiceDate = LocalDateTime.now()

        Repository.invoice.insert(invoice)

        val newCount = Repository.invoice.count()
        Assertions.assertEquals(oldCount + 1, newCount)

        Repository.invoice.delete(invoice)
    }

    @Test
    fun update() {
        val testCity = "김경태 도시"
        val invoice = Repository.invoice.getByPK(1)!!
        val citySource = invoice.billingCity
        invoice.billingCity = testCity

        Repository.invoice.update(invoice)

        val updatedInvoice = Repository.invoice.getByPK(invoice.invoicId)
        Assertions.assertEquals(testCity, updatedInvoice!!.billingCity)

        invoice.billingCity = citySource
        Repository.invoice.update(invoice)
    }

    @Test
    fun delete() {
        var invoice = Invoice()
        invoice.billingCity = "김경태 도시"
        invoice.customerId = 3

        Repository.invoice.insert(invoice)
        invoice = Repository.invoice.getLast()!!

        val oldCount = Repository.invoice.count()
        Repository.invoice.delete(invoice)
        val newCount = Repository.invoice.count()
        Assertions.assertEquals(oldCount - 1, newCount)
    }

    @Test
    fun getLast() {
        val lastInvoice = Repository.invoice.getLast()
        val invoices = Repository.invoice.getAll()
        Assertions.assertEquals(invoices[invoices.size - 1].invoicId, lastInvoice!!.invoicId)
    }
}