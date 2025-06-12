package com.javaex.posvo;

public class CategorySalesVO {
    private String categoryName;
    private int quantity;
    private int sales;

    public CategorySalesVO() {}

    public CategorySalesVO(String categoryName, int quantity, int sales) {
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.sales = sales;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSales() {
        return sales;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}