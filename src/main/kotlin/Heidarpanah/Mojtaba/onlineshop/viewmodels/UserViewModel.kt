package Heidarpanah.Mojtaba.onlineshop.viewmodels

import Heidarpanah.Mojtaba.onlineshop.models.customers.Customer
import Heidarpanah.Mojtaba.onlineshop.models.customers.User
import Heidarpanah.Mojtaba.onlineshop.models.invoices.Invoice
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne

data class UserViewModel(
    var id: Long,
    var userName: String = "",
    var password: String = "",
    var oldPassword: String = "",
    var repeatPassword: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var phone: String = "",
    var postalCode: String = "",
    var customerId: Long = 0,
    var token:String=""
) {

    constructor(user: User) : this(
        id = user.id,
        userName = user.username,
        firstName = user.customer!!.firstName,
        lastName = user.customer!!.lastName,
        address = user.customer!!.address,
        phone = user.customer!!.phone,
        postalCode = user.customer!!.postalCode,
        customerId = user.customer!!.id
    )

    fun convertToUser(): User {
        return User(
            id,
            userName,
            password,
            convertToCustomer()
        )
    }

    fun convertToCustomer(): Customer {
        return Customer(
            customerId,
            firstName,
            lastName,
            address,
            phone,
            postalCode
        )
    }
}