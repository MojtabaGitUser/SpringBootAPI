package Heidarpanah.Mojtaba.onlineshop.repositories.invoices

import Heidarpanah.Mojtaba.onlineshop.models.invoices.InvoiceItem
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface InvoiceItemRepository:PagingAndSortingRepository<InvoiceItem,Long> {

    @Query("from InvoiceItem where Invoice.id= :invoiceId")
    fun findAllByInvoiceId(invoiceId:Long):List<InvoiceItem>?
}