package Heidarpanah.Mojtaba.onlineshop.models.products

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class ProductCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: String = "",
    val image:String="",
    @JsonIgnore //For Ignore Recursion Error Because of ProductCategory and product Entity Relationship
    @OneToMany(mappedBy = "category")
    val products: Set<Product>? = null
)
