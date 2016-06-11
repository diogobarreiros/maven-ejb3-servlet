package com.diogo.ejb;

import java.io.PrintWriter;

import javax.ejb.Local;

@Local
public interface ExampleBeanLocal {

	public void init();
	public void destroy();
	public void afterBegin();
	public void beforeCompletion();
	public void afterCompletion(boolean committed);
	public void addItem(String item, PrintWriter out);
	public void methodMandatory(PrintWriter out);
	public void methodNever(PrintWriter out);
	public void methodNotSupported(PrintWriter out);
	public void methodRequired(PrintWriter out);
	public void methodRequiresNew(PrintWriter out);
	public void methodSupports(PrintWriter out);
	
}
