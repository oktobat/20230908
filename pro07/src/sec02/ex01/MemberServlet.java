package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec01.ex01.MemberDAO;
import sec01.ex01.MemberVO;

@WebServlet("/member3")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		MemberDAO dao = new MemberDAO();
		  
		  String command = request.getParameter("command");
		  if (command != null && command.equals("addMember")) {
			  String _id = request.getParameter("id");
		      String _pwd = request.getParameter("pwd");
		      String _name = request.getParameter("name");
		      String _email = request.getParameter("email");
		      String _edomain = request.getParameter("edomain");
		      String email = _email + "@" + _edomain;
		      String[] arrhobby = request.getParameterValues("hobby");
		      String hobby = "";
		      if (arrhobby != null) {
		    	  for (int i=0; i<arrhobby.length; i++) {
		    		  if (i==arrhobby.length-1) {
		    			  hobby += arrhobby[i];
		    		  } else {
		    			  hobby += arrhobby[i]+"/";
		    		  }
				  }
		      }
		      
		      MemberVO vo = new MemberVO();
		      vo.setId(_id);
		      vo.setPwd(_pwd);
		      vo.setName(_name);
		      vo.setEmail(email);
		      vo.setHobby(hobby);
		      dao.addMember(vo);

		  } else if (command != null && command.equals("delMember")) {
			  String id = request.getParameter("id");
			  dao.delMember(id);
		  } 
		  
		  response.setContentType("text/html;charset=utf-8");
	      PrintWriter out = response.getWriter();	
	      List<MemberVO> list = dao.listMembers();
		
	      out.print("<html><body>");
	      out.print("<table border=1><tr>");
	      out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>취미</td><td>가입일</td></tr>");
	      
	      for (int i=0; i<list.size(); i++) {
	    	  MemberVO memberVO = (MemberVO) list.get(i);
	    	  String id = memberVO.getId();
	  		  String pwd = memberVO.getPwd();
	  		  String name = memberVO.getName();
	  		  String email = memberVO.getEmail();
	  		  Date joinDate = memberVO.getJoinDate();
	  		  String hobby = memberVO.getHobby();
	  		  out.print("<tr><td>"+id+
	  				  "</td><td>"+pwd+
	  				  "</td><td>"+name+
	  				  "</td><td>"+email+
	  				  "</td><td>"+hobby+
	  				  "</td><td>"+joinDate+
	  				  "<a href='/pro07/member3?command=delMember&id="+id+"'>삭제</a></td></tr>");
	      }
	      out.print("</table><a href='/pro07/memberForm.html'>새회원 등록하기</a></body></html>");
	      
	      
	}

}
