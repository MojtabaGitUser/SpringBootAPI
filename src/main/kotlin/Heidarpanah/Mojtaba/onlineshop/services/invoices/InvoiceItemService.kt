package Heidarpanah.Mojtaba.onlineshop.services.invoices

import Heidarpanah.Mojtaba.onlineshop.services.products.ProductService
import Heidarpanah.Mojtaba.onlineshop.models.invoices.InvoiceItem
import Heidarpanah.Mojtaba.onlineshop.repositories.invoices.InvoiceItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceItemService {

    @Autowired
    private lateinit var repository: InvoiceItemRepository

    @Autowired
    private lateinit var productService: ProductService

    fun getById(id: Long): InvoiceItem? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllByInvoiceId(invoiceId: Long): List<InvoiceItem> {
        return repository.findAllByInvoiceId(invoiceId)!!.toList()
    }

    fun addItem(invoiceItem: InvoiceItem): InvoiceItem {
        if (invoiceItem.quantity <= 0)
            throw Exception("Invalid Quantity")
        if (invoiceItem.product?.id == null ||
            invoiceItem.product!!.id <= 0
        )
            throw Exception("Invalid Product")

        val productPrice = productService.getPriceById(invoiceItem.product!!.id)
        invoiceItem.unitPrice = productPrice!!
        return repository.save(invoiceItem)
    }

}