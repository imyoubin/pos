package com.javaex.vo;

public class StaticVO {

	// 일별
	// 메뉴명 수량 금액

	// 필드

	private String menuName;
	private int menuQuantity;
	private int menuPrice;
	
	private int orderDate;

	private int mTotalQuantity;
	private int mTotalPrice;

	// 생성자
	public StaticVO() {

	}

	public StaticVO(int menuPrice) {

		this.menuPrice = menuPrice;
	}

	public StaticVO(int mTotalQuantity, int mTotalPrice) {
		super();
		this.mTotalQuantity = mTotalQuantity;
		this.mTotalPrice = mTotalPrice;
	}

	public StaticVO(String menuName, int menuQuantity, int menuPrice) {

		this.menuName = menuName;
		this.menuQuantity = menuQuantity;
		this.menuPrice = menuPrice;
	}
	
	// 메소드gs
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getmenuQuantity() {
		return menuQuantity;
	}

	public void setmenuQuantity(int menuQuantity) {
		this.menuQuantity = menuQuantity;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public int getorderDate() {
		return orderDate;
	}

	public void setorderDate(int orderDate) {
		this.orderDate = orderDate;
	}

	public int getmTotalQuantity() {
		return mTotalQuantity;
	}

	public void setmTotalQuantity(int mTotalQuantity) {
		this.mTotalQuantity = mTotalQuantity;
	}

	public int getmTotalPrice() {
		return mTotalPrice;
	}

	public void setmTotalPrice(int mTotalPrice) {
		this.mTotalPrice = mTotalPrice;
	}

	// 메소드 일반

	@Override
	public String toString() {
		return "StaticVO [menuName=" + menuName + ", menuQuantity=" + menuQuantity + ", menuPrice=" + menuPrice
				+ ", orderDate=" + orderDate + ", mTotalQuantity=" + mTotalQuantity + ", mTotalPrice=" + mTotalPrice
				+ "]";
	}

}
