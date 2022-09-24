package Heidarpanah.Mojtaba.onlineshop.models.customers

import Heidarpanah.Mojtaba.onlineshop.models.invoices.Invoice
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    var username:String ="",
    var password:String = "",
    @OneToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer?=null,

    @OneToMany(mappedBy = "user")
    var invoices: Set<Invoice>? = null)
