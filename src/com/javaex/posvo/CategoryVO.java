package com.javaex.posvo;

public class CategoryVO {

	 // 필드
    private int categoryId;
    private String categoryName;
    private String categoryDesc;
    private String emoji;
    private String regDate;

    // 생성자
    public CategoryVO() {
    	
    }

    public CategoryVO(int categoryId, String categoryName, String categoryDesc, String emoji, String regDate) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.emoji = emoji;
        this.regDate = regDate;
    }

    public CategoryVO(String categoryName, String categoryDesc, String emoji) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.emoji = emoji;
    }

    // 메소드gs
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }
    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getEmoji() {
        return emoji;
    }
    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    // 메소드일반
    @Override
    public String toString() {
        return categoryId + " | " + categoryName + " | " + categoryDesc + " | " + emoji + " | " + regDate;
    }
}