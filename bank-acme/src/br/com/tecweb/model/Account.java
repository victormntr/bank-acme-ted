/**
 * 
 */
package br.com.tecweb.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author cbgomes
 *
 */
@Data
public class Account {

	private Long id;
	private String number;
	private String agency;
	private Double balance;
	private Client client;
	
	private List<Transaction> transacoes = new ArrayList<>();

	// emprestimo
	// extrato
	// pagamento

	public Double sacar(Double value) {
		if (this.balance >= value) {
			this.balance -= value;
		}
		return this.balance;
	}

	public Double depositar(Double value) {
		this.transacoes.add(createTransaction(this, value, "deposito"));
		return this.balance += value;
	}
	
	public Double transferencia(Account destino, Double value) {
		if(this.balance >= value) {
			this.sacar(value);
			destino.depositar(value);
		}
		return this.balance;
	}

	private Transaction createTransaction(Account accunt, Double value, String typeTransaction) {
		Transaction transaction = new Transaction();
		transaction.setAccount(accunt);
		transaction.setValue(value);
		transaction.setTypeTransaction(typeTransaction);
		transaction.setDateTimeTransaction(formatDate(LocalDateTime.now()));
		
		return transaction;
	}
	
	private String formatDate(LocalDateTime date) {
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String data =  date.format(formatter);
		
		return data;
	}
}

