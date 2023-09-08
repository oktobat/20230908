package sec02.ex01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BooksDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public BooksDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/test");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BooksVO> bookSearch(String bookname_) {
		List<BooksVO> list = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from books where book_name Like concat('%',?,'%')";
		    pstmt =	con.prepareStatement(query);
		    pstmt.setString(1, bookname_);
		    ResultSet rs = pstmt.executeQuery();
		    while(rs.next()) {
			      int id = rs.getInt("book_id");
			      String name = rs.getString("book_name");
			      String company = rs.getString("book_company");
			      int price = rs.getInt("book_price");
			      BooksVO vo = new BooksVO();
			      vo.setBook_id(id);
			      vo.setBook_name(name);
			      vo.setBook_company(company);
			      vo.setBook_price(price);
			      list.add(vo);
			}
		    rs.close();
		    pstmt.close();
		    con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
	
	
	
}
