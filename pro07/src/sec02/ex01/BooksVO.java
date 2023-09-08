package sec02.ex01;

public class BooksVO {
	
	private int book_id;
	private String book_name;
	private String book_company;
	private int book_price;
	
	public BooksVO() {
		System.out.println("MemberVO 생성자 호출");
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_company() {
		return book_company;
	}

	public void setBook_company(String book_company) {
		this.book_company = book_company;
	}

	public int getBook_price() {
		return book_price;
	}

	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}
	
	
	
	
}
