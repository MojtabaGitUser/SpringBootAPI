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
    val id: Long = 0,
    val authority: String = "",
    val status: Int = 0,
    val refId: String = "",
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    val invoice: Invoice? = null
) {
}