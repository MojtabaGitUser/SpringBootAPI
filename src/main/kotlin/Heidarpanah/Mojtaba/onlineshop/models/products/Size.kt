package Heidarpanah.Mojtaba.onlineshop.models.products

import javax.persistence.*

@Entity
data class Size(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: Int = 0,
    @ManyToMany
    val products: Set<Product>? = null
)
