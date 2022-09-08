package Heidarpanah.Mojtaba.onlineshop.models.invoices

import Heidarpanah.Mojtaba.onlineshop.models.customers.User
import Heidarpanah.Mojtaba.onlineshop.models.enums.InvoiceStatus
import javax.persistence.*

@Entity
data class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long=0,
    val number:Int=0,
    val status:InvoiceStatus=InvoiceStatus.notPayed,
    val addDate:String="",
    val paymentDate:String="",

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User?=null,

    @OneToMany(mappedBy = "invoice")
    val items : Set<InvoiceItem>?=null,

    @OneToMany(mappedBy = "invoice")
    val transactions : Set<Transaction>?=null


                   )
