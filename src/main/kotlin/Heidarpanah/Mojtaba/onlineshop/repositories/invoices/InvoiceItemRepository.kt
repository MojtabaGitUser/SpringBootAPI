package Heidarpanah.Mojtaba.onlineshop.repositories.invoices

import Heidarpanah.Mojtaba.onlineshop.models.invoices.InvoiceItem
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceItemRepository : PagingAndSortingRepository<InvoiceItem, Long> {

    @Query("from InvoiceItem where invoice.id = :invoiceId")
    fun findAllByInvoiceId(invoiceId: Long): List<InvoiceItem>
}