package Heidarpanah.Mojtaba.onlineshop.services.invoices

import Heidarpanah.Mojtaba.onlineshop.services.customers.UserService
import Heidarpanah.Mojtaba.onlineshop.models.enums.InvoiceStatus
import Heidarpanah.Mojtaba.onlineshop.models.invoices.Invoice
import Heidarpanah.Mojtaba.onlineshop.repositories.invoices.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.Calendar

@Service
class InvoiceService {

    @Autowired
    private lateinit var repository: InvoiceRepository

    @Autowired
    private  lateinit var invoiceItemService: InvoiceItemService

    @Autowired
    private lateinit var userService: UserService
     fun insert(data: Invoice,currentUser: String): Invoice {

         if(data.items!!.isEmpty()||data.items==null)
             throw Exception("Items Are Empty")
         if(data.user==null|| data.user!!.id==null||data.user!!.id<=0)
             throw Exception("Invalid User Id")

         val user = userService.getUserByUsername(currentUser)
         if(user==null || user.id != data.user.id)
             throw Exception("You do not have teh permission to access this data")

         var dt= Calendar.getInstance()
         data.status = InvoiceStatus.notPayed
         data.addDate = "${dt.get(Calendar.YEAR)}" + "-" +"${dt.get(Calendar.MONTH)}"+ "-" +"${dt.get(Calendar.DAY_OF_MONTH)}"+
                 " ${dt.get(Calendar.HOUR)}" + ":${dt.get(Calendar.MINUTE)}" + ":${dt.get(Calendar.SECOND)}"
         data.paymentDate=""
         data.transactions=null
         data.items!!.forEach{
             invoiceItemService.addItem(it)
         }
        return repository.save(data)
    }
     fun update(data: Invoice,currentUser: String): Invoice? {
        var oldData = getById(data.id,currentUser) ?: return null
        oldData.paymentDate = data.paymentDate
        oldData.status = data.status

        return repository.save(oldData)
    }


    fun getById(id: Long,currentUser: String): Invoice? {

        val data = repository.findById(id)
        if (data.isEmpty) return null
        val user = userService.getUserByUsername(currentUser)
        if(user==null || user.id != data.get().user!!.id)
            throw Exception("You do not have teh permission to access this data")
        return data.get()
    }



    fun getAllByUserId(userId: Long, pageIndex: Int, pageSize: Int,currentUser:String): List<Invoice>? {

        val user = userService.getUserByUsername(currentUser)
        if(user==null || user.id != userId)
            throw Exception("You do not have teh permission to access this data")

        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        val invoiceList: List<Invoice>? = repository.findAllByUserId(userId,pageRequest).toList()
        return invoiceList

    }



    fun getAllCount(): Long {
        return repository.count()
    }

}