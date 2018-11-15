package jms.repository;


import jms.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTransactionRepository extends JpaRepository<Transaction,Long> {}
