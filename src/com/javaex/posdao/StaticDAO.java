package com.javaex.posdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.posvo.StaticVO;

public class StaticDAO {
	
	

	// 필드
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;

		private String driver = "com.mysql.cj.jdbc.Driver";
		private String url = "jdbc:mysql://localhost:3306/web_db";
		private String id = "web";
		private String pw = "web";

	
	//생성자
	public StaticDAO() {
		
	}
	
	
	// DB연결 메소드 - 공통
	private void connect() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원 정리 메소드 -공통
	private void close() {
		// 5. 자원정리
		try {

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

	
	//일별매출리스트
	public List<StaticVO> daySalesSelect(){
		
		//리스트
		List<StaticVO> dSalesList = new ArrayList<StaticVO>();
		
		// 0. import java.sql.*;
		
		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();	

		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query +=" select 	 m.menu_name ";
			query +="           ,sum(o.quantity) as quantity ";
			query +="      	    ,sum(m.menu_price*o.quantity) as price";
			query +=" from menu m , orders o ";
			query +=" where m.menu_id = o.menu_id ";
			query +=" and date(o.order_time) = date(now())";
			query +=" group by m.menu_name ; ";

			
			
			// 바인딩 
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();

		    // 4.결과처리 (java 리스트로 만든다)
			while(rs.next()) {
				
			
				String menuName = rs.getString("menu_name");
				int menuQuantity = rs.getInt("quantity");
				int menuPrice = rs.getInt("price");
			
	
				
				//데이터 객체로 만들기(묶기)
				StaticVO staticVO = new StaticVO(menuName, menuQuantity, menuPrice);
				
				//묶은 데이터를 리스트에 달기
				dSalesList.add(staticVO);
			}

			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		// 5. 자원정리
		this.close();
		
		return dSalesList;
		
	}
	
	
	
	//일별매출리스트
	public List<StaticVO> dayTotalSalesSelect(){
		
		//리스트
		List<StaticVO> dtotalSalesList = new ArrayList<StaticVO>();
		
		// 0. import java.sql.*;
		
		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();	

		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query +="select 	 sum(o.quantity) as quantity ";
			query +="           ,sum(m.menu_price*o.quantity) as price ";
			query +=" from menu m , orders o ";
			query +=" where m.menu_id = o.menu_id ";
			query +=" and date(o.order_time) = date(now())";


			
			// 바인딩 
			pstmt = conn.prepareStatement(query);

			
			//실행
			rs = pstmt.executeQuery();

		    // 4.결과처리 (java 리스트로 만든다)
			while(rs.next()) {
				
			
				int mTotalQuantity = rs.getInt("quantity");
				int mTotalPrice = rs.getInt("price");
			
	
				
				//데이터 객체로 만들기(묶기)
				StaticVO staticVO = new StaticVO(mTotalQuantity, mTotalPrice);
				
				//묶은 데이터를 리스트에 달기
				dtotalSalesList.add(staticVO);
			}

			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		// 5. 자원정리
		this.close();
		
		return dtotalSalesList;
		
	}
	
	

    public List<StaticVO> categorySale() {

       List<StaticVO> csList = new ArrayList<StaticVO>();

       // 연결
       connect();

       try {
          // 쿼리 준비 바인딩 실행
          String query = "";
          query += "select m.menu_id ";
          query += "    , m.menu_name ";
          query += "    , m.menu_price ";
          query += "from category c ";
          query += "   , menu m ";
          query += "where c.category_id =m.category_id ";

          // 바인딩
          pstmt = conn.prepareStatement(query);

          // 실행
          rs = pstmt.executeQuery();

          // 4.결과처리
          while (rs.next()) {

             // 쿼리 결과를 변수에 담는다
             int menuId = rs.getInt("menu_id");
             String menuName = rs.getString("menu_name");
             int menuPrice = rs.getInt("menu_price");

             // posVO 로 묶는다
             StaticVO staticVO = new StaticVO(menuId, menuName, menuPrice);

             // 리스트에에 추가
             csList.add(staticVO);

          }

       } catch (Exception e) {
          System.out.println("error:" + e);
       }

       close();

       return csList;

    }

//    public List<OrderVO> categorySaleTotal() {
//       
//       List<OrderVO> cstList = new ArrayList<OrderVO>();
//
//       // 연결
//       connect();
//
//       try {
//          // 쿼리 준비 바인딩 실행
//          String query = "";
//          query += "select count(*) tot_cnt ";
//          query += "   , sum(m.menu_price*o.quantity) tot_sum ";
//          query += "from category c ";
//          query += "     , menu m ";
//          query += "    , orders o ";
//          query += "where o.menu_id = m.menu_id ";
//          query += "and m.category_id = c.category_id ";
//
//          // 바인딩
//          pstmt = conn.prepareStatement(query);
//
//          // 실행
//          rs = pstmt.executeQuery();
//
//          // 4.결과처리
//          while (rs.next()) {
//
//             // 쿼리 결과를 변수에 담는다
//             int totCnt = rs.getInt("tot_cnt");
//             int totSum = rs.getInt("tot_sum");
//
//             // posVO 로 묶는다
//             StaticVO staticVO = new StaticVO(totCnt,totSum);
//
//             // 리스트에에 추가
//             cstList.add(staticVO);
//
//          }
//
//       } catch (Exception e) {
//          System.out.println("error:" + e);
//       }
//
//       close();
//
//       return cstList;
//       
//    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
