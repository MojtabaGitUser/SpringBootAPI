package Heidarpanah.Mojtaba.onlineshop.models.products

import javax.persistence.*

@Entity
data class Color(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: String = "",
    val hexValue: String = "",
    @ManyToMany
    val products: Set<Product>? = null
)
