package pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PosDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";

	public PosDAO() {}

	//DB연결 메소드-공통
	private void connect() {    //메인에서는 사용하지 못함
		
		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	//자원정리 메소드-공통
	private void close() {
	    try {
	        if (rs != null) {
	            rs.close();
	        }
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	//메뉴 등록
			public int posInsert(String name, String desc) {
				int count = -1;
				
				// 0. import java.sql.*;
				
				// 1. JDBC 드라이버 (MySQL) 로딩
				// 2. Connection 얻어오기
				this.connect();
				
				try {
					
					// 3. SQL문 준비 / 바인딩 / 실행
					//SQL문 준비 
					String query = "";
					query += "select	category_id as '카테고리번호'";
					query += " category_name as '카테고리명'";
					query += " category_desc as '카테고리설명'";
					query += "emoji as '이모티콘'";
					query += "reg_date as '등록날짜'";
					query += "from category";
									
					System.out.println(query);
					
					//바인딩
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, name);   //메소드의 파라미터
					pstmt.setString(2, desc);   //메소드의 파라미터

					//실행
					count = pstmt.executeUpdate();
					
					// 4.결과처리
					
				} catch (SQLException e) {
					System.out.println("error:" + e);
				} 
				
				// 5. 자원정리
				this.close();
				
				return count;
			}
			
			// 메뉴 수정
			public int posUpdate(int posId, String name, String desc) {
			    int count = -1;

			    // 1. DB 연결
			    this.connect();

			    try {
			        // 2. SQL문 준비
			        String query = "";
			        query += " update pos ";
			        query += " set pos_name = ?, ";
			        query += " pos_desc = ? ";
			        query += " where pos_id = ? ";

			        // 3. 바인딩
			        pstmt = conn.prepareStatement(query);
			        pstmt.setString(1, name);
			        pstmt.setString(2, desc);
			        pstmt.setInt(3, posId);

			        // 4. 실행
			        count = pstmt.executeUpdate();

			    } catch (SQLException e) {
			        System.out.println("error:" + e);
			    } 

			    // 5. 자원 정리
			    this.close();

			    return count;
			}
			
			// 메뉴 삭제
			public int posDelete(int posId) {
			    int count = -1;
			    this.connect();

			    try {
			        String query = "DELETE FROM book WHERE book_id = ?";
			        pstmt = conn.prepareStatement(query);
			        pstmt.setInt(1, posId);
			        count = pstmt.executeUpdate();
			    } catch (SQLException e) {
			        System.out.println("error:" + e);
			    }

			    this.close();
			    return count;
			}
			// 전제조회
			public List<PosVO> PosSelectList() {
			    List<PosVO> baList = new ArrayList<>();
			    this.connect();

			    try {
			        String query = "";
			        query += " SELECT bo.book_id, ";
			        query += "        bo.book_name, ";
			        query += "        bo.book_desc, ";
			        query += "        au.author_id, ";
			        query += "        au.author_name, ";
			        query += "        au.author_desc ";
			        query += " FROM book bo ";
			        query += " JOIN author au ON bo.author_id = au.author_id ";
			        query += " ORDER BY bo.book_id ASC ";

			        pstmt = conn.prepareStatement(query);
			        rs = pstmt.executeQuery();

			        while (rs.next()) {
			            int posId = rs.getInt("_id");
			            String posName = rs.getString("book_name");
			            String posDesc = rs.getString("book_desc");
			            int posId = rs.getInt("author_id");
			            String authorName = rs.getString("author_name");
			            String authorDesc = rs.getString("author_desc");

			            PosVO vo = new PosVO(bookId, bookName, bookDesc,
			                                               authorId, authorName, authorDesc);
			            baList.add(vo);
			        }

			    } catch (SQLException e) {
			        System.out.println("error:" + e);
			    }

			    this.close();
			    return baList;
			}
			
			// 책 1권 정보 조회
			public PosVO posSelectOne(int bookId) {
			    PosVO posVO = null;
			    this.connect();

			    try {
			        String query = "";
			        query += " SELECT book_id, ";
			        query += "        book_name, ";
			        query += "        book_desc ";
			        query += " FROM book ";
			        query += " WHERE book_id = ? ";

			        pstmt = conn.prepareStatement(query);
			        pstmt.setInt(1, bookId);

			        rs = pstmt.executeQuery();

			        if (rs.next()) {
			            int id = rs.getInt("book_id");
			            String name = rs.getString("book_name");
			            String desc = rs.getString("book_desc");

			            posVO = new PosVO(id, name, desc);
			        }

			    } catch (SQLException e) {
			        System.out.println("error: " + e);
			    }

			    this.close();
			    return posVO;
			}
	}