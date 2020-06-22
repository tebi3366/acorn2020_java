package test.main;

import test.dao.MemberDao;
import test.dto.MemberDto;

public class MainClass10 {
	public static void main(String[] args) {
		//만일 MemberDao 객체의 참조값이 필요 하다면?
		//MemberDao dao=new MemberDao(); 직접 객체 생성 불가
		
		//static 메소드를 이용해서 MemberDao 객체의 참조값을 받아올수 있다.
		MemberDao dao=MemberDao.getInstance();
		
		MemberDto dto=new MemberDto();
		dto.setName("덩어리");
		dto.setAddr("상도동");
		//MemberDao 객체의 메소드를 활용해서 저장할수있다.
		dao.insert(dto);
		
		MemberDao dao2=MemberDao.getInstance();
		//수정할 회원이 정보라면
		MemberDto dto2=new MemberDto(1, "김구라", "독산동");
		dao.update(dto2);
	}
}
