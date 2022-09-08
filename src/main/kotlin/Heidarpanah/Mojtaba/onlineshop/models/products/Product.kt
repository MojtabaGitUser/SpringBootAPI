package Heidarpanah.Mojtaba.onlineshop.models.products

import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0 ,
    val title:String="",
    val image:String="",
    val visitCount:Int=0,
    val addDate:String,

    val size:Int,
    val description:String,
    @ManyToOne
    val category: ProductCategory?=null,
    @ManyToMany
    val colors: Set<Color>?=null
       )
