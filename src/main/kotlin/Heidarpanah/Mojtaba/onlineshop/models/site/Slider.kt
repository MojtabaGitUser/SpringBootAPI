package Heidarpanah.Mojtaba.onlineshop.models.site

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Slider(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val Image: String = "",
    val title: String = "",
    val subTitle: String = "",
    val link: String = ""
)
