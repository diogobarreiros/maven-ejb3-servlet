package com.diogo.ejb;

import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.BeforeCompletion;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * Session Bean implementation class ExampleBean
 */
@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class ExampleBean implements ExampleBeanLocal {

    @Override
    @PostConstruct
	public void init() {
		System.out.println("ExampleBean: init");
	}
	
	@Override
	@PreDestroy
	public void destroy() {
		System.out.println("ExampleBean: destroy");
	}
	
	@Override
	@AfterBegin
	public void afterBegin(){
		System.out.println("Uma nova transação foi iniciada.");
	}
	
	@Override
	@BeforeCompletion
	public void beforeCompletion(){
		System.out.println("A transação está para ser committed.");
	}
	
	@Override
	@AfterCompletion
	public void afterCompletion(boolean committed) {
		System.out.println("Uma transação foi concluída, "
				+ "segue se a transação foi committed "
				+ "valor : " + committed);
	}
	
	@Override
	public void addItem(String item, PrintWriter out) {
		out.println(item + " item adicionado");
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void methodMandatory(PrintWriter out) {
		out.println("MANDATORY: O método será executado dentro dessa mesma transação. "
				+ "Caso o cliente chame o método fora de uma transação, "
				+ "um erro será lançado e o código do método não será executado.");
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void methodNever(PrintWriter out) {
		out.println("NEVER: O método será executado normalmente sem criar nenhuma transação. "
				+ "Caso o cliente esteja envolvido em uma transação no momento da chamada, "
				+ "um erro será lançado e o código do método não será executado.");
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void methodNotSupported(PrintWriter out) {
		out.println("NOT_SUPPORTED: O método sempre será executado fora de uma transação. "
				+ "Se o cliente estiver dentro de uma transação, "
				+ "essa é suspensa até que o método execute seu código e retorne.");
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void methodRequired(PrintWriter out) {
		out.println("REQUIRED: Se um cliente invoca o método dentro de uma transação, "
				+ "o método será executado dentro desta transação. "
				+ "Se o método for invocado fora de uma transação, será criada uma nova transação.");
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void methodRequiresNew(PrintWriter out) {
		out.println("REQUIRES_NEW: Independentemente do cliente estar rodando ou não dentro de uma transação, "
				+ "uma nova transação será criada para a execução desse método");
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void methodSupports(PrintWriter out) {
		out.println("SUPPORTS: Se o cliente chamar o método fora de uma transação, "
				+ "nenhuma será criada e o método será executado fora de uma transação. "
				+ "Se o cliente chamar dentro de uma transação, o método será executado dentro dessa mesma transação.");
		
	}

}
