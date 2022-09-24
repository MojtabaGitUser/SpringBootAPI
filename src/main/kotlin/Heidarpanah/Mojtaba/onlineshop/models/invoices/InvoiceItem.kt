package Heidarpanah.Mojtaba.onlineshop.models.invoices

import Heidarpanah.Mojtaba.onlineshop.models.products.Product
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
data class InvoiceItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product?=null,
    val quantity:Int = 0,
    var unitPrice: Long = 0,
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    val invoice: Invoice?=null
    )
