package test.dto;

public class MemoDto {
	private int num;
	private String content;
	private String regdate;
	public MemoDto() {}
	public MemoDto(int num, String content, String regdate) {
		super();
		this.num = num;
		this.content = content;
		this.regdate = regdate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num; //참조값을 this로 가리킨다 필드 혹은 메소드 ... 
		//this.num은 필드를 가르키고있다.
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
