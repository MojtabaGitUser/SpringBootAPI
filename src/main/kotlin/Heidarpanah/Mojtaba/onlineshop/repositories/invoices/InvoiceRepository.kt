package Heidarpanah.Mojtaba.onlineshop.repositories.invoices

import Heidarpanah.Mojtaba.onlineshop.models.invoices.Invoice
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface InvoiceRepository:PagingAndSortingRepository<Invoice,Long> {

    @Query("from Invoice where user.id = :userId")
    fun findAllByUserId(userId:Long,pageable:Pageable):List<Invoice>
}