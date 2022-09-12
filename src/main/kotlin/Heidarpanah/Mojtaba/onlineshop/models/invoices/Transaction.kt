package Heidarpanah.Mojtaba.onlineshop.models.invoices

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var authority: String = "",
    var status: Int = 0,
    var refId: String = "",
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null
) {
}