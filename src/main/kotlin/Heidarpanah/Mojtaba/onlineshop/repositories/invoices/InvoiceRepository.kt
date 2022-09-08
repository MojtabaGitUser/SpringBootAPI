package Heidarpanah.Mojtaba.onlineshop.repositories.invoices

import Heidarpanah.Mojtaba.onlineshop.models.invoices.Invoice
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.repository.PagingAndSortingRepository

interface InvoiceRepository:PagingAndSortingRepository<Invoice,Long> {
}