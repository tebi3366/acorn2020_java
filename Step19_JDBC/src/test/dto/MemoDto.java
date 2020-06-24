package test.dto;

import java.sql.Date;

public class MemoDto {
	//회원 한명의 정보를 담을 필드 선언
	private int num;
	private String content;
	private Date date;
	//default 생성자 만들기
	public MemoDto() {}
	//인자로 필드에 저장할 값을 전달 받는 생성자
	public MemoDto(int num, String content, Date date) {
		super();
		this.num = num;
		this.content = content;
		this.date = date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setRegdate(Date date) {
		this.date = date;
	}
	public void setRegdate(String string) {
		// TODO Auto-generated method stub
		
	}
	
}
