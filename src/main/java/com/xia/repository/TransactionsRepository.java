package com.xia.repository;

import com.xia.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long>{

}
