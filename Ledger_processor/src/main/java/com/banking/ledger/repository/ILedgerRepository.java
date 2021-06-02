package com.banking.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.ledger.ledgers.Ledgers;

@Repository
public interface ILedgerRepository extends JpaRepository<Ledgers, Long>
		{

}
