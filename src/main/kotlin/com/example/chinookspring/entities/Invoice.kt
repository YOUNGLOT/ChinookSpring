package entities

import java.time.LocalDateTime

class Invoice: SingleKeyEntity<Int>() {
    var invoicId: Int = 0
    var customerId: Int = 0
    var invoiceDate: LocalDateTime = LocalDateTime.now()
    var billingAddress: String = ""
    var billingCity: String = ""
    var billingState: String? = ""
    var billingCountry: String = ""
    var billingPostalCode: String? = ""
    var total: Int = 0

    override val keyValue1: Int
        get() = invoicId
}
