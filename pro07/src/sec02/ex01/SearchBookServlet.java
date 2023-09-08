package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchBookServlet
 */
@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookname = request.getParameter("bookname");
		BooksDAO dao = new BooksDAO();
		List<BooksVO> list = dao.bookSearch(bookname);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<table border='1'>");
		out.print("<tr>");
		out.print("<td>도서번호</td>");
		out.print("<td>도서명</td>");
		out.print("<td>출판사</td>");
		out.print("<td>가격</td>");
		out.print("</tr>");
		for (int i=0; i<list.size(); i++) {
		   	BooksVO vo = (BooksVO) list.get(i);   // list[i]
		   	int id = vo.getBook_id();
		   	String name = vo.getBook_name();
		   	String company = vo.getBook_company();
		   	int price = vo.getBook_price();
		   	out.print("<tr>");
		   	out.print("<td>");
		   	out.print(id);
		   	out.print("</td>");
		   	out.print("<td>");
		   	out.print(name);
		   	out.print("</td>");
		   	out.print("<td>");
		   	out.print(company);
		   	out.print("</td>");
		   	out.print("<td>");
		   	out.print(price);
		   	out.print("</td>");
		   	out.print("</tr>");
		}
		out.print("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
