package pos;

public class PosVO {
	
	// 필드
	private int category_id;
	private String category_name;
	private String category_desc;
	private String emoji;	
	private String reg_date;
	
	// 생성자
	public PosVO() {
	}
	
	public PosVO(int category_id, String category_name, String category_desc, String emoji, String reg_date) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_desc = category_desc;
		this.emoji = emoji;
		this.reg_date = reg_date;
	}

	//메소드gs
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_desc() {
		return category_desc;
	}

	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	//메소드일반
	@Override
	public String toString() {
		return "PosVO [category_id=" + category_id + ", category_name=" + category_name + ", category_desc="
				+ category_desc + ", emoji=" + emoji + ", reg_date=" + reg_date + "]";
	}
}
