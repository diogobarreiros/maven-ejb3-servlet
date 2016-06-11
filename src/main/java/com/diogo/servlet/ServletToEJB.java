package com.diogo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import com.diogo.ejb.ExampleBeanLocal;

/**
 * Servlet implementation class ServletToEJB
 */
@WebServlet("/ServletToEJB")
public class ServletToEJB extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	ExampleBeanLocal exampleBeanLocal;
	
	@Resource
	private UserTransaction ut;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletToEJB() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try{
			ut.begin();
			exampleBeanLocal.addItem("Item 'teste'", out);
			exampleBeanLocal.methodMandatory(out);
			exampleBeanLocal.methodNotSupported(out);
			exampleBeanLocal.methodRequiresNew(out);
			out.println("SUPPORTS sendo executado dentro de uma transação");
			exampleBeanLocal.methodSupports(out);
			ut.commit();
			
			exampleBeanLocal.methodRequired(out);
			exampleBeanLocal.methodNever(out);
			out.println("SUPPORTS sendo executado fora de uma transação");
			exampleBeanLocal.methodSupports(out);
		}catch(Exception e){
			e.printStackTrace();
		}
		out.println("Teste EJB3 finalizado!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
