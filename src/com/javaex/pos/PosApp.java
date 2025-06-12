package com.javaex.pos;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.javaex.dao.CategoryDAO;
import com.javaex.dao.CategorySalesDAO;
import com.javaex.dao.HourStaticDAO;
import com.javaex.dao.MenuDAO;
import com.javaex.dao.MenuStaticDAO;
import com.javaex.dao.OrderDAO;
import com.javaex.dao.PaymentDAO;
import com.javaex.dao.StaticDAO;
import com.javaex.vo.CategorySalesVO;
import com.javaex.vo.CategoryVO;
import com.javaex.vo.HourStaticVO;
import com.javaex.vo.MenuCateVO;
import com.javaex.vo.MenuStaticVO;
import com.javaex.vo.MenuVO;
import com.javaex.vo.OrderVO;
import com.javaex.vo.PaymentVO;
import com.javaex.vo.StaticVO;

public class PosApp {

	public static void main(String[] args) {

		OrderDAO orderDAO = new OrderDAO();
		PaymentDAO paymentDAO = new PaymentDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
		MenuDAO menuDAO = new MenuDAO();
		StaticDAO staticDAO = new StaticDAO();
		HourStaticDAO hourStaticDAO = new HourStaticDAO();
		MenuStaticDAO menuStaticDAO = new MenuStaticDAO();
        CategorySalesDAO categorySalesDAO = new CategorySalesDAO();

		Scanner sc = new Scanner(System.in);

		int mainNum;
		int orderNum;
		int tableNum;
		int menuNum = 0;
		int count;
		int regYn;
		int editOrderNum;
		int editCount;
		int editYn;
		int oUp;
		int delOrderNum;
		int delCount;
		int compareMenu;
		int orderIdNum;
		int paymentNum;
		int staticMenuNum;

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
					System.out.println("-----------------------------------------------------------------------------------------------------");
					System.out.println("주문번호\t 메뉴명\t\t가격\t\t 수량\t\t 총금액\t\t 테이블번호");

					// 오더리스트
					List<OrderVO> posOrderList = orderDAO.orderSelect();

					for (int i = 0; i < posOrderList.size(); i++) {
						System.out.println(posOrderList.get(i).getOrderId() + "\t" + posOrderList.get(i).getMenuName()
								+ "  " + posOrderList.get(i).getMenuPrice() + "  " + posOrderList.get(i).getQuantity()
								+ "  " + posOrderList.get(i).getTotalPrice() + "  "
								+ posOrderList.get(i).getTableNumber());
					}
					System.out.println("-----------------------------------------------------------------------------------------------------");
					System.out.println("1.주문하기  2.주문수정  3.주문삭제  4.주문결제  5.테이블결제  0.메인홈");

					System.out.print(">");
					orderNum = sc.nextInt();

					if (orderNum == 0) {
						System.out.println();
						break;
					} else if (orderNum == 1) {

						System.out.println("");
						System.out.println("▶주문하기");
						System.out.println("-----------------------------------------------------------------------------------------------------");

						// 메뉴리스트
						List<OrderVO> lList = orderDAO.listSelect();

						for (int i = 0; i < lList.size(); i++) {
							System.out.println(lList.get(i).getMenuId() + ". " + lList.get(i).getMenuName() + " - "
									+ lList.get(i).getMenuPrice() + "원");
						}

						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("*0.나가기");

						while (true) {

							System.out.println();
							System.out.print("테이블번호> ");
							tableNum = sc.nextInt();
							if (tableNum == 99) {
								break;
							}

							System.out.print("메뉴번호> ");
							menuNum = sc.nextInt();
							if (tableNum == 99) {
								break;
							}

							System.out.print("수량> ");
							count = sc.nextInt();
							if (count == 99) {
								break;
							}

							System.out.println("주문하시겠습니까?");
							System.out.println("1.예  2.아니오");
							regYn = sc.nextInt();

							if (regYn == 1) {
								// 주문
								orderDAO.orderInsert(tableNum, count, menuNum);
								System.out.println("+++주문완료+++");
							} else {
								System.out.println("+++주문취소+++");
								break;
							}
						}

					} else if (orderNum == 2) {

						System.out.println("");
						System.out.println("▶주문수정");
						System.out.println("-----------------------------------------------------------------------------------------------------");

						for (int i = 0; i < posOrderList.size(); i++) {
							System.out.println(posOrderList.get(i).getOrderId() + "\t"
									+ posOrderList.get(i).getMenuName() + "  " + posOrderList.get(i).getMenuPrice()
									+ "  " + posOrderList.get(i).getQuantity() + "  "
									+ posOrderList.get(i).getTotalPrice() + "  "
									+ posOrderList.get(i).getTableNumber());
						}
						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("*0.나가기");

						while (true) {
							System.out.println();
							System.out.print("주문번호> ");
							editOrderNum = sc.nextInt();
							if (editOrderNum == 99) {
								break;
							}
							System.out.print("수량> ");
							count = sc.nextInt();
							if (count == 99) {
								break;
							}

							System.out.println("수정하시겠습니까?");
							System.out.println("1.예  2.아니오");
							regYn = sc.nextInt();

							if (regYn == 1) {
								// 주문수정
								orderDAO.orderUpdate(editOrderNum, count);
								System.out.println("+++주문수정완료+++");

							} else {
								System.out.println("+++주문수정취소+++");
								break;
							}
						}

					} else if (orderNum == 3) {

						System.out.println("");
						System.out.println("▶주문삭제");
						System.out.println("-----------------------------------------------------------------------------------------------------");

						for (int i = 0; i < posOrderList.size(); i++) {
							System.out.println(posOrderList.get(i).getOrderId() + "\t"
									+ posOrderList.get(i).getMenuName() + "  " + posOrderList.get(i).getMenuPrice()
									+ "  " + posOrderList.get(i).getQuantity() + "  "
									+ posOrderList.get(i).getTotalPrice() + "  "
									+ posOrderList.get(i).getTableNumber());
						}
						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("*0.나가기");

						while (true) {
							System.out.println();
							System.out.print("삭제할 주문번호> ");
							delOrderNum = sc.nextInt();
							if (delOrderNum == 99) {
								break;
							} else {
								// 주문삭제
								int oDel = orderDAO.orderDelete(delOrderNum);
							}
						}
					} else if (orderNum == 4) {// 결제 강민
						System.out.println("");
						System.out.println("▶주문결제");
						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("주문번호\t 메뉴명\t\t가격\t\t 수량\t\t 총금액\t\t 테이블번호");
						List<PaymentVO> orderList = paymentDAO.orderSelectList();

						for (int i = 0; i < orderList.size(); i++) {
							System.out.println(orderList.get(i).getOrderId() + "\t\t" + orderList.get(i).getMenuName()
									+ "\t\t" + orderList.get(i).getMenuPrice() + "\t\t" + orderList.get(i).getQuantity()
									+ "\t\t" + orderList.get(i).getTotalPrice() + "\t\t"
									+ orderList.get(i).getTableNumber());
						}
						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("");
						System.out.print("결제할 주문번호> ");
						orderIdNum = sc.nextInt();

						for (int i = 0; i < orderList.size(); i++) {

							if (orderIdNum == orderList.get(i).getOrderId()) {
								System.out.println("결제하시겠습니까?");
								System.out.println("1.예   2.아니오");
								paymentNum = sc.nextInt();

								if (paymentNum == 1) {
									int c01 = paymentDAO.payOrderId(orderIdNum);

									if (c01 > 0) {
										System.out.println("+++결제완료+++");
										break;
									} else if (c01 < 0) {
										System.out.println("알 수 없는 오류 발생");
									} else {
										System.out.println("결제되지 않았습니다.");
									}

								} else {
									break;
								}
							}
						}
					} else {
						System.out.println("존재하지 않는 메뉴번호입니다.");
					}
				}

				break;
			case 2:// 카테고리 유빈

				while (true) {
					System.out.println();
					System.out.println("■카테고리관리");
					System.out.println("-----------------------------------------------------------------------------------------------------");
					System.out.println("카테고리코드\t카테고리명\t설명\t이모티콘");

					List<CategoryVO> categoryList = categoryDAO.categorySelect();
					for (CategoryVO c : categoryList) {
						System.out.println(c.getCategoryId() + "\t\t" + c.getCategoryName() + "\t"
								+ (c.getCategoryDesc() == null ? "" : c.getCategoryDesc()) + "\t"
								+ (c.getEmoji() == null ? "" : c.getEmoji()));
					}

					System.out.println("-----------------------------------------------------------------------------------------------------");
					System.out.println("1.카테고리등록  2.카테고리수정  3.카테고리삭제  0.메인홈");
					System.out.print("> ");
					int choice = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 정리

					if (choice == 0) {
						break;
					} else if (choice == 1) {
						// 카테고리 등록
						System.out.println("▶카테고리등록");
						System.out.println("*0.나가기");

						while (true) {
							System.out.print("카테고리명> ");
							String name = sc.nextLine();
							if (name.equals("0")) {
								System.out.println("카테고리 등록이 취소되었습니다.");
								break;
							}

							System.out.print("설명>");
							String desc = sc.nextLine();
							if (desc.equals("0")) {
								System.out.println("카테고리 등록이 취소되었습니다.");
								break;
							}

							System.out.print("이모티콘>");
							String emoji = sc.nextLine();
							if (emoji.equals("0")) {
								System.out.println("카테고리 등록이 취소되었습니다.");
								break;
							}

							categoryDAO.insertCategory(name, desc, emoji);
							System.out.println("카테고리가 등록되었습니다.");
							break;
						}

					} else if (choice == 2) {
						// 카테고리 수정
						System.out.println("▶카테고리수정");
						System.out.println("*0.나가기");

						while (true) {
							System.out.print("수정할 카테고리코드> ");
							int id = sc.nextInt();
							sc.nextLine();
							if (id == 99) {
								System.out.println("수정이 취소되었습니다.");
								break;
							}

							System.out.print("새 카테고리명> ");
							String name = sc.nextLine();
							if (name.equals("0")) {
								System.out.println("수정이 취소되었습니다.");
								break;
							}

							System.out.print("새 설명> ");
							String desc = sc.nextLine();
							if (desc.equals("0")) {
								System.out.println("수정이 취소되었습니다.");
								break;
							}

							System.out.print("새 이모티콘> ");
							String emoji = sc.nextLine();
							if (emoji.equals("0")) {
								System.out.println("수정이 취소되었습니다.");
								break;
							}

							System.out.println("수정하시겠습니까?");
							System.out.println("1.예 2.아니오");
							System.out.print(">");
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
						System.out.println("▶카테고리삭제");
						System.out.println("*0.나가기");

						while (true) {
							System.out.print("삭제할 카테고리코드> ");
							int id = sc.nextInt();
							sc.nextLine();
							if (id == 0) {
								System.out.println("삭제가 취소되었습니다.");
								break;
							}

							System.out.println("삭제하시겠습니까?");
							System.out.println("1.예 2.아니오");
							System.out.print(">");
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
			case 3:// 메뉴수진
				while (true) {
					System.out.println(" ");
					System.out.println("■메뉴관리");
					System.out.println("-----------------------------------------------------------------------------------------------------");
					System.out.println("코드  카테고리명  메뉴코드  메뉴명  가격");

					List<MenuVO> menuList = menuDAO.menuSelect();

					for (int i = 0; i < menuList.size(); i++) {
						System.out.println(menuList.get(i).getCategoryId() + "       "
								+ menuList.get(i).getCategoryName() + "      " + menuList.get(i).getMenuId() + "      "
								+ menuList.get(i).getMenuName() + "       " + menuList.get(i).getMenuPrice() + "원");

						//0일때 
					}
					System.out.println("-----------------------------------------------------------------------------------------------------");
					System.out.println("1.메뉴등록 2.메뉴수정 3.메뉴삭제 0.메인홈");
					System.out.print("> ");
					mainNum = sc.nextInt();

					if (mainNum == 0) {
						break;
					} else if (mainNum == 1) {
						System.out.println("▶메뉴등록");
						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("코드  카테고리명  메뉴코드  메뉴명  가격");

						for (int i = 0; i < menuList.size(); i++) {
							System.out.println(menuList.get(i).getCategoryId() + "\t"
									+ menuList.get(i).getCategoryName() + "\t" + menuList.get(i).getMenuId() + "\t"
									+ menuList.get(i).getMenuName() + "\t" + menuList.get(i).getMenuPrice() + "원");

						}

						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("*0.나가기");

						while (true) {

							//List<MenuVO> caCodeList = menuDAO.caCodeSelect();
							
							System.out.println(" ");
							System.out.print("카테고리코드> ");
							int cId = sc.nextInt();
										
							//db갔다와
							MenuCateVO menuCateVO = menuDAO.cateChoiceSelect(cId);
							
							if(menuCateVO.getCnt()==0) {
								System.out.println();
								System.out.println("!!카테고리 없음!!");
								break;
							} else if (cId == 0) {
								System.out.println("메뉴등록이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}//else if(cId =! )

							System.out.print("메뉴명> ");
							String mName = sc.next();
							if (mName == "0") {
								System.out.println("메뉴등록이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							System.out.print("가격> ");
							int mPrice = sc.nextInt();
							if (mPrice == 0) {
								System.out.println("메뉴등록이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							menuDAO.menuInsert(mName, mPrice, cId);

						} // 메뉴등록 끝

						// 메뉴수정
					} else if (mainNum == 2) {
						System.out.println("▶메뉴수정");
						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println(" 코드  카테고리명  메뉴코드  메뉴명  가격");

						for (int i = 0; i < menuList.size(); i++) {
							System.out.println(menuList.get(i).getCategoryId() + "\t"
									+ menuList.get(i).getCategoryName() + "\t" + menuList.get(i).getMenuId() + "\t"
									+ menuList.get(i).getMenuName() + "\t" + menuList.get(i).getMenuPrice() + "원");

						}

						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("*0.나가기");

						while (true) {

							System.out.println(" ");
							System.out.print("수정할 메뉴코드> ");
							int mId = sc.nextInt();
							if (mId == 0) {
								System.out.println("수정이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							System.out.print("메뉴명> ");
							String mName = sc.next();
							if (mName.equals("0")) {
								System.out.println("수정이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}

							System.out.print("가격> ");
							int mPrice = sc.nextInt();

							System.out.println("수정하시겠습니까?");
							System.out.println(" 1.예     2.아니오.");
							System.out.print(">");
							int upNum = sc.nextInt();

							if (upNum == 1) {
								menuDAO.menuUpdate(mId, mName, mPrice);

							} else if (upNum == 2) {
								System.out.println("수정이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;

							} else if (mPrice == 0) {
								System.out.println("수정이 취소되었습니다. 메뉴관리로 이동합니다.");
								break;
							}
						}
					}
				}

				break;
			case 4:
				while (true) {
					System.out.println(" ");
					System.out.println("■매출통계");

					System.out.println("-----------------------------------------------------------------------------------------------------");
					System.out.println("1. 일별 매출 조회     2. 시간대별 매출 조회    3.메뉴별 매출 조회    4.카테고리별 매출 조회  0.메인홈");
					System.out.println("-----------------------------------------------------------------------------------------------------");
					System.out.print("> ");
					staticMenuNum = sc.nextInt();

					if (staticMenuNum == 0) {
						break;

					} else if (staticMenuNum == 1) {
						System.out.println("▶일별 매출 조회");

						System.out.println("");
						System.out.println("<일별 매출 조회>");

						LocalDate today = LocalDate.now();
						System.out.println(today);

						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println(" 메뉴명    수량    금액 ");

						List<StaticVO> dSalesList = staticDAO.daySalesSelect();
						for (int i = 0; i < dSalesList.size(); i++) {
							System.out.println(dSalesList.get(i).getMenuName() + "\t"
									+ dSalesList.get(i).getmenuQuantity() + "\t" + dSalesList.get(i).getMenuPrice());
						}

						System.out.println("-----------------------------------------------------------------------------------------------------");

						List<StaticVO> dtotalSalesList = staticDAO.dayTotalSalesSelect();
						for (int i = 0; i < dtotalSalesList.size(); i++) {
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

					} else if (staticMenuNum == 2) {
						System.out.println("▶ 2. 시간대별 매출 조회 ");
						System.out.println("<시간대별 매출 조회>");

						LocalDate today = LocalDate.now();
						System.out.println(today);

						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("시간대 \t\t 건수 \t\t 금액");
						List<HourStaticVO> salesHourList = hourStaticDAO.salesByHourList();

						for (int i = 0; i < salesHourList.size(); i++) {
							System.out.println(salesHourList.get(i).getOrderTime() + "\t\t"
									+ salesHourList.get(i).getOrderCount() + "\t\t" + salesHourList.get(i).getSales());
						}
						System.out.println("-----------------------------------------------------------------------------------------------------");

					} else if (staticMenuNum == 3) {
						System.out.println("▶ 3.메뉴별 매출 조회");
						System.out.println("<메뉴별 매출 조회>");

						LocalDate today = LocalDate.now();
						System.out.println(today);

						System.out.println("-----------------------------------------------------------------------------------------------------");
						System.out.println("시간대 \t\t 건수 \t\t 금액");
						List<MenuStaticVO> salesMenuList = menuStaticDAO.salesByMenuList();

						for (int i = 0; i < salesMenuList.size(); i++) {
							System.out.println(salesMenuList.get(i).getMenuName() + "\t\t"
									+ salesMenuList.get(i).getTotalQuantity() + "\t\t"
									+ salesMenuList.get(i).getSales());
						}
						System.out.println("-----------------------------------------------------------------------------------------------------");
					}else if (staticMenuNum == 4) {
                        System.out.println("▶카테고리별 매출 조회");
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
				System.out.println("존재하지 않는 메뉴번호입니다.");
			}

		}

		sc.close();

	}

}
