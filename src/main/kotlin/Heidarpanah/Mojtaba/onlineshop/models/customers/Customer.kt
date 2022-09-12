package Heidarpanah.Mojtaba.onlineshop.models.customers

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy
import java.util.StringJoiner
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    var firstName:String = "",
    var lastName:String= "",
    var address:String= "",
    var phone:String= "",
    var postalCode:String = "",
    @OneToOne(mappedBy = "customer")
    var user:User?= null)



