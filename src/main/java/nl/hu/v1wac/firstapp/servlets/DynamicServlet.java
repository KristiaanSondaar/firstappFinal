package nl.hu.v1wac.firstapp.servlets;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/DynamicServlet.do")

public class DynamicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {

		String name = req.getParameter("username");
		int number1 = Integer.parseInt(req.getParameter("number1"));
		int number2 = Integer.parseInt(req.getParameter("number2"));

		int calculation = 0;

		if (req.getParameter("plus") != null) {
			calculation = number1 + number2;
		} else if (req.getParameter("min") != null) {
			calculation = number1 - number2;
		} else if (req.getParameter("keer") != null) {
			calculation = number1 * number2;
		} else if (req.getParameter("delen") != null) {
			if (number2 != 0) {
				calculation = number1 / number2;
			} else {
				calculation = 0;
			}
		} else {
			calculation = 0;
		}

		PrintWriter out = resp.getWriter();

		resp.setContentType("text/html");

		out.println("<!DOCTYPE html>");

		out.println("<html>");

		out.println(" <title>Dynamic Example</title>");

		out.println(" <body>");

		out.println(" <h2>Hello</h2>");

		out.println(" <h2>The answer is " + calculation + "!</h2>");

		out.println(" </body>");

		out.println("</html>");

	}

}