package Heidarpanah.Mojtaba.onlineshop.Services.invoices

import Heidarpanah.Mojtaba.onlineshop.models.invoices.Invoice
import Heidarpanah.Mojtaba.onlineshop.models.invoices.Transaction
import Heidarpanah.Mojtaba.onlineshop.models.products.Product
import Heidarpanah.Mojtaba.onlineshop.models.products.ProductCategory
import Heidarpanah.Mojtaba.onlineshop.repositories.invoices.InvoiceRepository
import Heidarpanah.Mojtaba.onlineshop.repositories.products.ProductCategoryRepository
import Heidarpanah.Mojtaba.onlineshop.repositories.products.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class InvoiceService {

    @Autowired
    lateinit var repository: InvoiceRepository

    private fun insert(data: Invoice): Invoice {
        return repository.save(data)
    }

    private fun update(data: Invoice): Invoice? {
        var oldData = getById(data.id) ?: return null
        oldData.paymentDate = data.paymentDate
        oldData.status = data.status

        return repository.save(oldData)
    }


    fun getById(id: Long): Invoice? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }



    fun getAllInvoiceByUserId(userId: Long, pageIndex: Int, pageSize: Int): List<Invoice>? {

        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        val invoiceList: List<Invoice>? = repository.findAllByUserId(userId,pageRequest).toList()
        return invoiceList

    }



    fun getAllCount(): Long {
        return repository.count()
    }

}