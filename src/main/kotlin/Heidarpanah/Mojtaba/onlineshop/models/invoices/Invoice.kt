package Heidarpanah.Mojtaba.onlineshop.models.invoices

import Heidarpanah.Mojtaba.onlineshop.models.customers.User
import Heidarpanah.Mojtaba.onlineshop.models.enums.InvoiceStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long=0,
    var status:InvoiceStatus=InvoiceStatus.notPayed,
    var addDate:String="",
    var paymentDate:String="",

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User?=null,

    @OneToMany(mappedBy = "invoice")
    val items : Set<InvoiceItem>?=null,

    @OneToMany(mappedBy = "invoice")
    var transactions : Set<Transaction>?=null


                   )
