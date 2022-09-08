package Heidarpanah.Mojtaba.onlineshop.repositories.invoices

import Heidarpanah.Mojtaba.onlineshop.models.invoices.Transaction
import org.springframework.data.repository.PagingAndSortingRepository

interface TransactionRepository:PagingAndSortingRepository<Transaction,Long> {
}