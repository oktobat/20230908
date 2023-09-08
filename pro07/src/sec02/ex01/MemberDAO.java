package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
//import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

//	private static final String driver = "com.mysql.cj.jdbc.Driver";
//	private static final String url = "jdbc:mysql://localhost:3306/book_store";
//	private static final String user = "root";
//	private static final String pwd = "1234";
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/test");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
//			connDB();
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				String hobby = rs.getString("hobby");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				vo.setHobby(hobby);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addMember(MemberVO memberVO) {
		try { 
//			connDB();
			con = dataFactory.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			String hobby = memberVO.getHobby();
			String query = "insert into t_member";
			query += " (id, pwd, name, email, hobby)";
			query += " values(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, hobby);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	
	public void delMember(String id) {
		try {
//			connDB();
			con = dataFactory.getConnection();
			String query = "delete from t_member where id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public String okMember(String id_, String pwd_) {
		String info = "";
		try {
		con = dataFactory.getConnection();
		String query = "select * from t_member where id=?";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id_);
		ResultSet rs = pstmt.executeQuery();
		if ( rs.next() ) {
			String pwd = rs.getString("pwd");
			if (pwd.equals(pwd_)) {
				info = "회원입니다.";
			} else { 
				info = "비밀번호가 맞지 않습니다.";
			}
		} else {
			info = "일치하는 아이디가 없습니다.";
		}
		rs.close();
		pstmt.close();
		con.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return info;
	}
	
	
	
//	private void connDB() {
//		try {
//			Class.forName(driver);
//			System.out.println("MySQL 드라이버 로딩 성공");
//			con = DriverManager.getConnection(url, user, pwd);
//			System.out.println("Connection 생성 성공");
////			stmt = con.createStatement();
////			System.out.println("Statement 생성 성공");
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//	}
	
}
