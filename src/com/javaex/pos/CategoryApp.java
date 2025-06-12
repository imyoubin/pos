package com.javaex.pos;

import java.util.List;
import java.util.Scanner;

import com.javaex.posdao.CategoryDAO;
import com.javaex.posdao.OrderDAO;
import com.javaex.posvo.CategoryVO;
import com.javaex.posvo.OrderVO;

public class CategoryApp {
	public static void main(String[] args) {

		OrderDAO orderDAO = new OrderDAO();

		CategoryDAO categoryDAO = new CategoryDAO();
		
		Scanner sc = new Scanner(System.in);

		int mainNum;
		int orderNum;
		int tableNum;
		int menuNum;
		int count;
		int editOrderNum;
		int editCount;

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
				while (true) {
					System.out.println("");
					System.out.println("■테이블 주문 현황");
					System.out.println("----------------------------------------------------------");
					System.out.println("주문번호	메뉴명	가격  수량  금액  테이블번호");
					
					// 오더리스트
					List<OrderVO> posOrderList = orderDAO.orderSelect();

					for (int i = 0; i < posOrderList.size(); i++) {
						System.out.println(posOrderList.get(i).getOrderId() + "\t" 
										+ posOrderList.get(i).getMenuName() + "  "
										+ posOrderList.get(i).getMenuPrice() + "  "
										+ posOrderList.get(i).getQuantity() + "  "
										+ posOrderList.get(i).getTotalPrice() + "  "
										+ posOrderList.get(i).getTableNumber());
					}
					System.out.println("----------------------------------------------------------");
					System.out.println("1.주문하기  2.주문수정  3.주문삭제  4.주문결제  5.테이블결제  0.메인홈");

					System.out.print(">");
					orderNum = sc.nextInt();

					if (orderNum == 0) {
						break;
					} else if (orderNum == 1) {

						System.out.println("");
						System.out.println("▶주문하기");
						System.out.println("----------------------------------------------------------");

						// 메뉴리스트
						List<OrderVO> menuList = orderDAO.menuSelect();

						for (int i = 0; i < menuList.size(); i++) {
							System.out.println(menuList.get(i).getMenuId() + ". " + menuList.get(i).getMenuName()
									+ " - " + menuList.get(i).getMenuPrice() + "원");
						}

						System.out.println("----------------------------------------------------------");
						System.out.println("*99.나가기");
						
						while(true) {
							System.out.println();
							System.out.print("테이블번호>"); tableNum = sc.nextInt();
							if(tableNum == 99) {
								break;
							}
							System.out.print("메뉴번호>"); menuNum = sc.nextInt();
							if( menuNum == 99) {
								break;
							}
							System.out.print("수량>"); count = sc.nextInt();
							if(count == 99) {
								break;
							}
							// 주문
							int oIn = orderDAO.orderInsert(tableNum,count,menuNum);
						}

					} else if (orderNum == 2) {

						System.out.println("");
						System.out.println("▶주문수정");
						System.out.println("----------------------------------------------------------");

						for (int i = 0; i < posOrderList.size(); i++) {
							System.out.println(posOrderList.get(i).getOrderId() + "\t" 
											+ posOrderList.get(i).getMenuName() + "  "
											+ posOrderList.get(i).getMenuPrice() + "  "
											+ posOrderList.get(i).getQuantity() + "  "
											+ posOrderList.get(i).getTotalPrice() + "  "
											+ posOrderList.get(i).getTableNumber());
						}
						System.out.println("----------------------------------------------------------");
						System.out.println("99.나가기");
						
						while(true) {
							System.out.println();
							System.out.print("수정할 주문번호>"); editOrderNum = sc.nextInt();
							if(editOrderNum == 99) {
								break;
							}
							System.out.print("수량>"); editCount = sc.nextInt();
							if(editCount == 99) {
								break;
							}
							
							// 주문
							orderDAO.orderUpdate(editOrderNum,editCount);
						}

					} else if (orderNum == 4) {//결제 강민
						
					}

				}

				break;
			case 2: // 카테고리 관리
				
			    while (true) {
			        System.out.println();
			        System.out.println("카테고리관리");
			        System.out.println("-----------------------------------------------------");
			        System.out.println("카테고리코드\t카테고리명\t설명\t이모티콘");

			        List<CategoryVO> categoryList = categoryDAO.categorySelect();
			        for (CategoryVO c : categoryList) {
			            System.out.println(c.getCategoryId() + "\t\t" +
			                               c.getCategoryName() + "\t" +
			                               (c.getCategoryDesc() == null ? "" : c.getCategoryDesc()) + "\t" +
			                               (c.getEmoji() == null ? "" : c.getEmoji()));
			        }

			        System.out.println("-----------------------------------------------------");
			        System.out.println("1.카테고리등록  2.카테고리수정  3.카테고리삭제  0.메인홈");
			        System.out.print("> ");
			        int choice = sc.nextInt();
			        sc.nextLine(); // 입력 버퍼 정리

			        if (choice == 0) {
			            break;
			        } else if (choice == 1) {
			            // 카테고리 등록
			            System.out.println("카테고리등록 (99.나가기)");

			            while (true) {
			                System.out.print("카테고리명: ");
			                String name = sc.nextLine();
			                if (name.equals("99")) {
			                    System.out.println("카테고리 등록이 취소되었습니다.");
			                    break;
			                }

			                System.out.print("설명: ");
			                String desc = sc.nextLine();
			                if (desc.equals("99")) {
			                    System.out.println("카테고리 등록이 취소되었습니다.");
			                    break;
			                }

			                System.out.print("이모티콘: ");
			                String emoji = sc.nextLine();
			                if (emoji.equals("99")) {
			                    System.out.println("카테고리 등록이 취소되었습니다.");
			                    break;
			                }

			                categoryDAO.insertCategory(name, desc, emoji);
			                System.out.println("카테고리가 등록되었습니다.");
			                break;
			            }

			        } else if (choice == 2) {
			            // 카테고리 수정
			            System.out.println("카테고리수정 (99.나가기)");

			            while (true) {
			                System.out.print("수정할 카테고리코드: ");
			                int id = sc.nextInt();
			                sc.nextLine();
			                if (id == 99) {
			                    System.out.println("수정이 취소되었습니다.");
			                    break;
			                }

			                System.out.print("새 카테고리명: ");
			                String name = sc.nextLine();
			                if (name.equals("99")) {
			                    System.out.println("수정이 취소되었습니다.");
			                    break;
			                }

			                System.out.print("새 설명: ");
			                String desc = sc.nextLine();
			                if (desc.equals("99")) {
			                    System.out.println("수정이 취소되었습니다.");
			                    break;
			                }

			                System.out.print("새 이모티콘: ");
			                String emoji = sc.nextLine();
			                if (emoji.equals("99")) {
			                    System.out.println("수정이 취소되었습니다.");
			                    break;
			                }

			                System.out.println("수정하시겠습니까? (1.예 2.아니오)");
			                System.out.print("> ");
			                int confirm = sc.nextInt();
			                sc.nextLine();

			                if (confirm == 1) {
			                	categoryDAO.updateCategory(id, name, desc, emoji);
			                    System.out.println("카테고리가 수정되었습니다.");
			                } else {
			                    System.out.println("수정이 취소되었습니다.");
			                }
			                break;
			            }

			        } else if (choice == 3) {
			            // 카테고리 삭제
			            System.out.println("카테고리삭제 (99.나가기)");

			            while (true) {
			                System.out.print("삭제할 카테고리코드: ");
			                int id = sc.nextInt();
			                sc.nextLine();
			                if (id == 99) {
			                    System.out.println("삭제가 취소되었습니다.");
			                    break;
			                }

			                System.out.println("삭제하시겠습니까? (1.예 2.아니오)");
			                System.out.print("> ");
			                int confirm = sc.nextInt();
			                sc.nextLine();

			                if (confirm == 1) {
			                	categoryDAO.deleteCategory(id);
			                    System.out.println("카테고리가 삭제되었습니다.");
			                } else {
			                    System.out.println("삭제가 취소되었습니다.");
			                }
			                break;
			            }
			        } else {
			            System.out.println("잘못된 선택입니다.");
			        }
			    }
			    break;
			case 3://메뉴수진
				

				break;
			case 4:

				break;
			case 5:
				break;

//			default:
			}

		}

		sc.close();

//		//수정
//		int oUp = orderDAO.orderUpdate(1,3);
//		//삭제
//		int oDel = orderDAO.orderDelete(3);

		// 메뉴리스트
//		List<OrderVO> menuList = orderDAO.menuSelect();
//		
//		System.out.println("--------------------------------");
//		for(int i=0; i<menuList.size(); i++) {
//			System.out.println(menuList.get(i).getMenuId()+". "
//								+menuList.get(i).getMenuName()+" - "
//								+menuList.get(i).getMenuPrice()+"원");
//		}
//		System.out.println("--------------------------------");

	}

}
