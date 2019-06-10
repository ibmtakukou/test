package Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CardsDAO;

/**
 * Servlet implementation class servletcrl
 */
@WebServlet("/servletcrl")
public class servletcrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletcrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//CardsDAO cdao = new CardsDAO();
		String select = request.getParameter("select");
		if(select.equals("view")) {
			response.sendRedirect("card_list.jsp");
		}

		if(select.equals("add")) {
			addCard(request,response);
			response.sendRedirect("add_complete.html");
		}
		if(select.equals("del")) {
			delCard(request,response);
		}
		if(select.equals("detail")) {
			String code = request.getParameter("code");
			response.sendRedirect("card_detail.jsp?code="+code);
		}
		if(select.equals("reset")) {
			resetDB(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void addCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		CardsDAO cdao = new CardsDAO();
		String newcode = request.getParameter("code");
		String newname = request.getParameter("name");
		String newrare = request.getParameter("rare");
		String newprice = request.getParameter("price");
		cdao.add(newcode, newname, Integer.parseInt(newrare), Integer.parseInt(newprice));
	}

	private void delCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		CardsDAO cdao = new CardsDAO();
		cdao.remove(code);
		response.sendRedirect("delete_complete.jsp?code="+code+"&name="+URLEncoder.encode(name,"utf-8"));
	}

	private void resetDB(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		CardsDAO cdao = new CardsDAO();
		cdao.initialize();
		response.sendRedirect("reset_success.html");
	}


}
