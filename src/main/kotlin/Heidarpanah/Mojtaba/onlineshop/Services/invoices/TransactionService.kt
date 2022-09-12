package Heidarpanah.Mojtaba.onlineshop.Services.invoices

import Heidarpanah.Mojtaba.onlineshop.models.invoices.Transaction
import Heidarpanah.Mojtaba.onlineshop.repositories.invoices.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {

    @Autowired
    lateinit var repository: TransactionRepository

    //CRUD
    private fun insert(data: Transaction): Transaction {
        return repository.save(data)
    }

    private fun update(data: Transaction): Transaction? {
        var oldData = getById(data.id) ?: return null
        oldData.refId = data.refId
        oldData.status = data.status

        return repository.save(oldData)
    }

    fun getById(id: Long): Transaction? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }


}