package com.javaex.pos;

import java.util.List;
import java.util.Scanner;

import com.javaex.posdao.CategorySalesDAO;
import com.javaex.posvo.CategorySalesVO;

public class CategorySalesApp {
    public static void main(String[] args) {
    	
    	
            Scanner sc = new Scanner(System.in);
            int mainNum;

            CategorySalesDAO categorySalesDAO = new CategorySalesDAO();

            while (true) {
                System.out.println("--------------------------hi-pos--------------------------");
                System.out.println("1.주문결제  2.카테고리관리  3.메뉴관리  4.매출통계  5.포스종료");
                System.out.println("----------------------------------------------------------");
                System.out.print("> ");
                mainNum = sc.nextInt();

                if (mainNum == 5) {
                    System.out.println("포스 시스템을 종료합니다.");
                    break;
                }

                switch (mainNum) {
                    case 1:
                        // 주문결제 구현
                        break;
                    case 2:
                        // 카테고리 관리 구현
                        break;
                    case 3:
                        // 메뉴 관리 구현
                        break;
                    case 4:
                        while (true) {
                            System.out.println(" ");
                            System.out.println("매출통계");
                            System.out.println("----------------------------------------------------------");
                            System.out.println("1. 일별 매출 조회     2. 시간대별 매출 조회    3. 메뉴별 매출 조회    4. 카테고리별 매출 조회    0. 메인홈");
                            System.out.println("----------------------------------------------------------");
                            System.out.print("> ");
                            int statNum = sc.nextInt();

                            if (statNum == 0) {
                                break;  // 메인홈으로
                            } else if (statNum == 4) {
                                System.out.println("4 카테고리별 매출 조회");
                                System.out.println("<카테고리별 매출 조회>");
                                System.out.println("---------------------------------------------------");
                                System.out.println("카테고리명\t수량\t금액");

                                List<CategorySalesVO> cSalesList = categorySalesDAO.categorySalesSelect();

                                for (CategorySalesVO vo : cSalesList) {
                                    System.out.println(vo.getCategoryName() + "\t" + vo.getQuantity() + "\t" + vo.getSales());
                                }

                                System.out.println("---------------------------------------------------");

                                CategorySalesVO total = categorySalesDAO.categoryTotalSalesSelect();
                                System.out.println("총수량: " + total.getQuantity() + "\t총금액: " + total.getSales() + " 원");

                                System.out.println(" ");
                                System.out.println("1. 매출통계 화면으로 이동    0. 메인화면 이동");
                                System.out.print("> ");
                                int num = sc.nextInt();
                                if (num == 0) {
                                    break; // 메인화면 이동
                                }
                            } else {
                                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                            }
                        }
                        break;

                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            }

            sc.close();
        }
    }