package com.banking.ledger.configuration;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.banking.ledger.ledgers.Ledgers;

public class LedgerFieldSetMapper implements FieldSetMapper<Ledgers>{

	@Override
	public Ledgers mapFieldSet(FieldSet fieldSet) throws BindException {

		
		return null;
	}

}
