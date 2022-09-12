package Heidarpanah.Mojtaba.onlineshop.Services.invoices

import Heidarpanah.Mojtaba.onlineshop.models.invoices.InvoiceItem
import Heidarpanah.Mojtaba.onlineshop.repositories.invoices.InvoiceItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceItemService {

    @Autowired
    lateinit var repository: InvoiceItemRepository


    fun getById(id: Long): InvoiceItem? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }


    fun getAllByInvoiceId(invoiceId: Long): List<InvoiceItem>? {

        val invoiceItemList: List<InvoiceItem>? = repository.findAllByInvoiceId(invoiceId)?.toList()
        return invoiceItemList

    }


    fun getAllCount(): Long {
        return repository.count()
    }

}