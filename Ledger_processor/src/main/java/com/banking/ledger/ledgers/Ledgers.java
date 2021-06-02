package com.banking.ledger.ledgers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Ledgers {

	@Id
    @Column (name = "ID", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@NotNull
    @Column (name = "ledgerStatus",nullable = false)
	private String ledgerStatus;
	

    public String getLedgerStatus() {
		return ledgerStatus;
	}

	public void setLedgerStatus(String ledgerStatus) {
		this.ledgerStatus = ledgerStatus;
	}

	public Ledgers(long id,String ledgerName, String ledgerType,String ledgerDate,
			@NotNull Integer amount) {
		super();
		this.id = id;
		this.ledgerName = ledgerName;
		this.ledgerType = ledgerType;
		this.ledgerDate = ledgerDate;
		this.amount = amount;
	}
    
    public Ledgers() {
    	
    }

	@NotNull
    @Column (name = "ledgerName",nullable = false)
	private String ledgerName;
	
    @NotNull
    @Column (name = "ledgerType",nullable = false)
	private String ledgerType;
	
    @NotNull
    @Column (name = "ledgerDate",nullable = false)
	private String ledgerDate;
	
    @NotNull
    @Column (name = "amount",nullable = false)
	private Integer amount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}

	public String getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(String ledgerType) {
		this.ledgerType = ledgerType;
	}

	public String getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(String ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
