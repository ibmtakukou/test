package Test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CardsDAO;

/**
 * Servlet implementation class addservlet
 */
@WebServlet("/addservlet")
public class addservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
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
		String newcode = request.getParameter("code");
		String newname = request.getParameter("name");
		String newrare = request.getParameter("rare");
		String newprice = request.getParameter("price");
		cdao.add(newcode, newname, Integer.parseInt(newrare), Integer.parseInt(newprice));
		out.println("<html><head></head><body>");
		out.println("<p><center>追加完了</center></p>");
		out.println("<h3><center><a href=\"Top.html\">トップに戻る</a></center></h3>");
		out.println("</body></html>");
	}

}
