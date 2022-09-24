package Heidarpanah.Mojtaba.onlineshop.models.customers

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    var firstName:String = "",
    var lastName:String= "",
    var address:String= "",
    var phone:String= "",
    var postalCode:String = "",

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    var user: User?= null)



