package com.javaex.posdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javaex.posvo.CategorySalesVO;

public class CategorySalesDAO {

	public List<CategorySalesVO> categorySalesSelect() {
        List<CategorySalesVO> list = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_db", "root", "1234");
            String sql = "SELECT c.category_name, SUM(o.quantity) AS quantity, SUM(o.quantity * m.price) AS sales " +
                         "FROM orders o " +
                         "JOIN menu m ON o.menu_id = m.menu_id " +
                         "JOIN category c ON m.category_id = c.category_id " +
                         "GROUP BY c.category_name " +
                         "ORDER BY sales DESC";

            PreparedStatement pstmt = conn.prepareStatement(sql);
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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_db", "root", "1234");
            String sql = "SELECT SUM(o.quantity) AS total_quantity, SUM(o.quantity * m.price) AS total_sales " +
                         "FROM orders o " +
                         "JOIN menu m ON o.menu_id = m.menu_id";

            PreparedStatement pstmt = conn.prepareStatement(sql);
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
