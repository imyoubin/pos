package pos;

import java.sql.SQLException;
import java.util.List;

public class PosApp {

	public static void main(String[] args) {
			
        PosDAO posdao = new PosDAO();
      
        posdao.insertCategory(new Category("김밥","🇰🇷🍙"));
        posdao.insertCategory(new Category("분식", "🍚"));
        posdao.insertCategory(new Category("라면","🍜"));
        posdao.insertCategory(new Category("계절메뉴","🍜❄️"));
        posdao.insertCategory(new Category("세트메뉴","🍱🐷"));
        posdao.insertCategory(new Category("음료수","🥤"));
        
        System.out.println(posdao);
        
        List<Category> list = dao.getAllCategories();
        for (Category c : list) {
            System.out.println(c);
        }
    }
}
