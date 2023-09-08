package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		MemberDAO dao = new MemberDAO();
		String id_ = request.getParameter("id");
		String pwd_ = request.getParameter("pwd");
		String info = dao.okMember(id_, pwd_);
//		System.out.println(info);
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();	
	    out.print("<html><body>");
	    out.print("<p>");
	    out.print(info);
	    out.print("</p>");
	    out.print("</body></html>");
	}

}
