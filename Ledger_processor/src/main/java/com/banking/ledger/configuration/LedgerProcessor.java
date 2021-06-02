package com.banking.ledger.configuration;

import org.springframework.batch.item.ItemProcessor;

import com.banking.ledger.ledgers.Ledgers;

public class LedgerProcessor implements ItemProcessor<Ledgers, Ledgers>{

	@Override
	public Ledgers process(Ledgers item) throws Exception {
		final Ledgers ledgers = new Ledgers();
		return ledgers;
	}

}
