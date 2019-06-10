package Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CardsDAO;
import DTO.Card;

/**
 * Servlet implementation class dbservlet
 */
@WebServlet("/dbservlet")
public class dbservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public dbservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		CardsDAO cdao = new CardsDAO();
		ArrayList<Card> card = cdao.findAll();
		out.println("<html><head></head><body><table border = \"1\">");
		out.println("<th>CODE</th><th>NAME</th><th>RARE</th><th>PRICE</th><br>");
		for(Card c: card) {
			out.println("<tr>");
			out.println("<th>"+c.getCode()+"</th><th>"+c.getName()+"</th><th>"+c.getRare()+"</th><th>"+c.getPrice()+"</th><br>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<h3><a href=\"Top.html\">トップに戻る</a></h3>");
		out.println("</body></html>");

	}

}
