package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.CategorySalesVO;

public class CategorySalesDAO {

	public List<CategorySalesVO> categorySalesSelect() {
        List<CategorySalesVO> list = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_db", "web", "web");
            
            String 	query = "";
		            query += "SELECT c.category_name, ";
		            query += "SUM(o.quantity) AS quantity, ";
		            query += "SUM(o.quantity * m.menu_price) AS sales ";
		            query += "FROM orders o ";
		            query += "JOIN menu m ON o.menu_id = m.menu_id ";
		            query += "JOIN category c ON m.category_id = c.category_id ";
		            query += "GROUP BY c.category_name ";
		            query += "ORDER BY sales DESC";

            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String categoryName = rs.getString("category_name");
                int quantity = rs.getInt("quantity");
                int sales = rs.getInt("sales");
                list.add(new CategorySalesVO(categoryName, quantity, sales));
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public CategorySalesVO categoryTotalSalesSelect() {
        CategorySalesVO totalVO = new CategorySalesVO();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_db", "web", "web");
            String 	query = "";
		            query += "SELECT SUM(o.quantity) AS total_quantity, ";
		            query += "SUM(o.quantity * m.menu_price) AS total_sales ";
		            query += "FROM orders o ";
		            query += "JOIN menu m ON o.menu_id = m.menu_id";

            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalVO.setQuantity(rs.getInt("total_quantity"));
                totalVO.setSales(rs.getInt("total_sales"));
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalVO;
    }
}
