package Heidarpanah.Mojtaba.onlineshop.models.products

import javax.persistence.*

@Entity
data class ProductCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: String = "",
    @ManyToMany(mappedBy = "category")
    val products: Set<Product>? = null
)
