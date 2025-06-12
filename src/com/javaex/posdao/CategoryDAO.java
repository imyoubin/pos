package com.javaex.posdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.posvo.CategoryVO;

public class CategoryDAO {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/web_db?serverTimezone=UTC";
    private final String id = "web";
    private final String pw = "web";

    // DB 연결
    private void connect() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, id, pw);
        } catch (ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
    }

    // 자원 반납
    private void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
    }

    // 1) categorySelect() - 전체 카테고리 조회 (기존 첫번째 코드 스타일)
    public List<CategoryVO> categorySelect() {
        List<CategoryVO> list = new ArrayList<>();
        connect(); // DB 연결
        try {
            String sql = "SELECT * FROM category ORDER BY category_id ASC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoryVO vo = new CategoryVO(
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getString("category_desc"),
                    rs.getString("emoji"),
                    rs.getString("reg_date")
                );
                list.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(); // 자원정리
        }
        return list;
    }

    // 2) insertCategory() - 카테고리 등록
    public int insertCategory(String name, String desc, String emoji) {
        int count = -1;
        connect();
        try {
            String sql = "INSERT INTO category (category_name, category_desc, emoji) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, desc);
            pstmt.setString(3, emoji);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: 카테고리 등록 실패 - " + e);
        }
        close();
        return count;
    }

    // 3) updateCategory() - 카테고리 수정
    public int updateCategory(int id, String name, String desc, String emoji) {
        int count = -1;
        connect();
        try {
            String sql = "UPDATE category SET category_name = ?, category_desc = ?, emoji = ? WHERE category_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, desc);
            pstmt.setString(3, emoji);
            pstmt.setInt(4, id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: 카테고리 수정 실패 - " + e);
        }
        close();
        return count;
    }

    // 4) deleteCategory() - 카테고리 삭제
    public int deleteCategory(int id) {
        int count = -1;
        connect();
        try {
            String sql = "DELETE FROM category WHERE category_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                System.out.println("***해당 카테고리에 메뉴가 존재하여 삭제할 수 없습니다***");
                count = -1;
            } else {
                System.out.println("error: 카테고리 삭제 실패 - " + e);
            }
        }
        close();
        return count;
    }

    // 5) getAllCategories() - 카테고리 전체 조회 (두 번째 코드 스타일)
    public List<CategoryVO> getAllCategories() {
        List<CategoryVO> list = new ArrayList<>();
        connect();
        try {
            String sql = "SELECT category_id, category_name, category_desc, emoji, reg_date FROM category ORDER BY category_id ASC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoryVO vo = new CategoryVO(
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getString("category_desc"),
                    rs.getString("emoji"),
                    rs.getString("reg_date")
                );
                list.add(vo);
            }
        } catch (SQLException e) {
            System.out.println("error: 카테고리 조회 실패 - " + e);
        }
        close();
        return list;
    }
}
