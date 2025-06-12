package com.javaex.pos;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.javaex.posdao.CategorySalesDAO;
import com.javaex.posvo.StaticVO;

public class StaticApp {

	public static void main(String[] args) {

		CategorySalesDAO categorySalesDAO = new CategorySalesDAO();

		Scanner sc = new Scanner(System.in);

		int mainNum;

		while (true) {
			System.out.println("--------------------------hi-pos--------------------------");
			System.out.println("1.주문결제  2.카테고리관리  3.메뉴관리  4.매출통계  5.포스종료");
			System.out.println("----------------------------------------------------------");

			System.out.print(">");
			mainNum = sc.nextInt();

			if (mainNum == 5) {
				break;
			}

			switch (mainNum) {

			case 1:
				break;

			case 2:
				break;

			case 3:
				break;

			// 통계
			case 4:
				while (true) {
					System.out.println(" ");
					System.out.println("■매출통계");

					System.out.println("----------------------------------------------------------");
					System.out.println("1. 일별 매출 조회     2. 시간대별 매출 조회    3.메뉴별 매출 조회    4.카테고리별 매출 조회  0.메인홈");
					System.out.println("----------------------------------------------------------");
					System.out.print(">");
					mainNum = sc.nextInt();

					if (mainNum == 0) {
						break;

					} else if (mainNum == 1) {
						System.out.println("▶일별 매출 조회");

						System.out.println("");
						System.out.println("<일별 매출 조회>");

						LocalDate today = LocalDate.now();
						System.out.println(today);

						System.out.println("---------------------------------------------------");
						System.out.println(" 메뉴명    수량    금액 ");

						List<StaticVO> dSalesList = staticDAO.daySalesSelect();
						for (int i = 0; i < dSalesList.size(); i++) {
							System.out.println(dSalesList.get(i).getMenuName() + "\t"
									+ dSalesList.get(i).getmenuQuantity() + "\t" + dSalesList.get(i).getMenuPrice());
						}

						System.out.println("---------------------------------------------------");

						List<StaticVO> dtotalSalesList = staticDAO.dayTotalSalesSelect();
						for (int i = 0; i < dSalesList.size(); i++) {
							System.out.println("총수량: " + dtotalSalesList.get(i).getmTotalQuantity() + " " + "총금액: "
									+ dtotalSalesList.get(i).getmTotalPrice() + " 원");
						}

						System.out.println(" ");
						System.out.println("1.매출통계 화면으로 이동    0.메인화면 이동");
						System.out.print(">");
						int num = sc.nextInt();

						if (num == 0) {
							break;
						}
						
					}

				}

				break;

			case 5:
				break;

			default:
				System.out.println("다시 입력해주세요.");

			}
		}

	}

}	