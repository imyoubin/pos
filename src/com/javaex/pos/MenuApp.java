package com.javaex.pos;

import java.util.List;
import java.util.Scanner;

import com.javaex.posdao.MenuDAO;
import com.javaex.posvo.MenuVO;

public class MenuApp {
	public static void main(String[] args) {

		MenuDAO menuDAO = new MenuDAO();

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

			// 메뉴
			case 3:

				while (true) {
					System.out.println(" ");
					System.out.println("■메뉴관리");
					System.out.println("-----------------------------------------------------------");
					System.out.println(" 코드  카테고리명  메뉴코드  메뉴명  가격");

					List<MenuVO> menuList = menuDAO.menuSelect();

					for (int i = 0; i < menuList.size(); i++) {
						System.out.println(menuList.get(i).getCategoryId() + "       "
								+ menuList.get(i).getCategoryName() + "      " + menuList.get(i).getMenuId() + "      "
								+ menuList.get(i).getMenuName() + "       " + menuList.get(i).getMenuPrice() + "원");

					}
					System.out.println("----------------------------------------------------------");
					System.out.println(" 1.메뉴등록 2.메뉴수정 3.메뉴삭제 0.메인홈");
					System.out.print(">");
					mainNum = sc.nextInt();

					if (mainNum == 0) {
						break;
					} else if (mainNum == 1) {
						System.out.println("▶메뉴등록");
						System.out.println("-----------------------------------------------------------");
						System.out.println(" 코드  카테고리명  메뉴코드  메뉴명  가격");

						for (int i = 0; i < menuList.size(); i++) {
							System.out.println(menuList.get(i).getCategoryId() + "\t"
									+ menuList.get(i).getCategoryName() + "\t" + menuList.get(i).getMenuId() + "\t"
									+ menuList.get(i).getMenuName() + "\t" + menuList.get(i).getMenuPrice() + "원");

						}

						System.out.println("----------------------------------------------------------");
						System.out.println("                                                99.나가기");

						while (true) {
							
							System.out.println(" ");
							System.out.print(">카테고리코드: ");
							int cId = sc.nextInt();
							if (cId == 99) {
								System.out.println("메뉴등록이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							System.out.print(">메뉴명: ");
							String mName = sc.next();
							if (mName == "99") {
								System.out.println("메뉴등록이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							System.out.print(">가격:");
							int mPrice = sc.nextInt();
							if (mPrice == 99) {
								System.out.println("메뉴등록이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							menuDAO.menuInsert(mName, mPrice, cId);

						}// 메뉴등록 끝
						
						//메뉴수정
					} else if (mainNum == 2) {
						System.out.println("▶메뉴수정");
						System.out.println("-----------------------------------------------------------");
						System.out.println(" 코드  카테고리명  메뉴코드  메뉴명  가격");

						for (int i = 0; i < menuList.size(); i++) {
							System.out.println(menuList.get(i).getCategoryId() + "\t"
									+ menuList.get(i).getCategoryName() + "\t" + menuList.get(i).getMenuId() + "\t"
									+ menuList.get(i).getMenuName() + "\t" + menuList.get(i).getMenuPrice() + "원");

						}

						System.out.println("----------------------------------------------------------");
						System.out.println("                                                99.나가기");

						while (true) {

							System.out.println(" ");
							System.out.print(">수정할 메뉴코드: ");
							int mId = sc.nextInt();
							if (mId == 99) {
								System.out.println("수정이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							System.out.print(">메뉴명: ");
							String mName = sc.next();
							if (mName == "99") {
								System.out.println("수정이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							System.out.print(">가격:");
							int mPrice = sc.nextInt();
							
							System.out.println("수정하시겠습니까?");
							System.out.println(" 1.예     2.아니오.");
							System.out.print(">");
							int upNum = sc.nextInt(); 
							
							if(upNum == 1) {
								menuDAO.menuUpdate(mId, mName, mPrice);
							
							} else if(upNum == 2) {
								System.out.println("수정이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							
							} else if (mPrice == 99) {
								System.out.println("수정이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

						}

					//메뉴수정 끝 
					//메뉴삭제 시작
					}  else if (mainNum == 3) {
						System.out.println("▶메뉴삭제");
						System.out.println("-----------------------------------------------------------");
						System.out.println(" 코드  카테고리명  메뉴코드  메뉴명  가격");

						for (int i = 0; i < menuList.size(); i++) {
							System.out.println(menuList.get(i).getCategoryId() + "\t"
									+ menuList.get(i).getCategoryName() + "\t" + menuList.get(i).getMenuId() + "\t"
									+ menuList.get(i).getMenuName() + "\t" + menuList.get(i).getMenuPrice() + "원");

						}

						System.out.println("----------------------------------------------------------");
						System.out.println("                                                99.나가기");

						while (true) {

							System.out.println(" ");
							System.out.print(">삭제할 메뉴코드: ");
							int mId = sc.nextInt();
							if (mId == 99) {
								System.out.println("삭제가 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							System.out.println("삭제하시겠습니까?");
							System.out.println(" 1.예     2.아니오.");
							System.out.print(">");
							int delNum = sc.nextInt(); 
							
							if(delNum == 1) {
								menuDAO.menuDelete(mId);
							
							} else if(delNum == 2) {
								System.out.println("삭제가 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							
							} else if (mId == 99) {
								System.out.println("삭제가 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}
			
						}

					}
				}
				
				
				break;

			case 4:
				break;

			case 5:
				break;

			default:
				System.out.println("다시 입력해주세요.");

			


		

				}
			}
	}	
}
