package test.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import test.dao.MemoDao;
import test.dto.MemoDto;

/*	CREATE TABLE MEMO(
 *	num NUMBER PRIMARY KEY,
 *	content VARCHAR2(30),
 *	regdate DATE);
 *
 *	CREATE SEQUENCE MEMO_SEQ;
 * 	
 * 	위와 같이 테이블과 시퀀스를 만들고 해당 테이블에 데이터를
 * 	SELECT, INSERT,UPDATE,DELETE 기능을 수행할수 있는 MemoFrame 을 만들어보세요.
 * 
 * 	조건
 *  1. num 칼럼은 시퀀스를 이용해서 넣으세요.
 *  2. regdate 칼람(등록일)의 값은 SYSDATE 를 이용해서 넣으세요.
 *  3. 수정은 content 만 수정 가능하게 하세요.
 *  4. MemoDto, MemoDao 를 만들어서 프로그래밍 하세요.
 * 
 */
public class MemoFrame extends 
				JFrame implements ActionListener, PropertyChangeListener{
	//필드
	JTextField inputcontent;
	JTable table;
	DefaultTableModel model;
	
	
	//생성자
	public MemoFrame() {
		setLayout(new BorderLayout());
		
		JLabel label1=new JLabel("내용");
		inputcontent=new JTextField(10);
		
		
		JButton saveBtn=new JButton("저장");
		saveBtn.setActionCommand("save");
		saveBtn.addActionListener(this);
		
		//삭제 버튼 추가
		JButton deleteBtn=new JButton("삭제");
		deleteBtn.setActionCommand("delete");
		deleteBtn.addActionListener(this);
		
		JPanel panel=new JPanel();
		panel.add(label1);
		panel.add(inputcontent);
		
		panel.add(saveBtn);
		panel.add(deleteBtn);
		
		add(panel, BorderLayout.NORTH);
		
		//표형식으로 정보를 출력하기 위한 JTable
		table=new JTable();
		//칼럼명을 String[] 에 순서대로 준비
		String[] colNames= {"번호", "내용", "등록일"};
		//테이블에 출력할 정보를 가지고 있는 모델 객체 (칼럼명, row 갯수)
		model=new DefaultTableModel(colNames, 0) {
			//인자로 전달되는 행(row), 열(column) 수정 가능 여부를 리턴하는 메소드  
			@Override
			public boolean isCellEditable(int row, int column) {
				//만일 첫번째(0번째) 칼럼이면 수정이 불가 하도록 한다.
				if(column==0 || column==2) {
					return false;
				}
				return true;
			}
		
		};
		//모델을 테이블에 연결한다.
		table.setModel(model);
		//스크롤이 가능 하도록 테이블을 JScrollPane 에 감싼다.
		JScrollPane scroll=new JScrollPane(table);
		//JScrollPane  을 프레임의 가운데에 배치하기 
		add(scroll, BorderLayout.CENTER);
		//테이블에 회원 목록 출력하기
		displayMember();
		//테이블에서 발생하는 이벤트 리스너 등록 하기
		table.addPropertyChangeListener(this);
	}
	//테이블에 회원 목록을 출력하는 메소드 
	public void displayMember() {
		//row  의 갯수를 강제로 0 으로 지정해서 삭제 한다. 
		model.setRowCount(0);
		//회원 목록을 얻어와서 
		MemoDao dao=MemoDao.getInstance();
		List<MemoDto> list=dao.getList();
		for(MemoDto tmp:list) {
			//MemberDto 객체에 저장된 정보를 Object[] 객체에 순서대로 담는다.
			Object[] row= {tmp.getNum(), tmp.getContent(), tmp.getDate()};
			model.addRow(row);
		}
	}
	
	public static void main(String[] args) {
		MemoFrame f=new MemoFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100,100,800,500);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals("save")) {
			//입력한 문자열을 읽어와서
			String content=inputcontent.getText();
			
			//MemverDto 객체담아서
			MemoDto dto=new MemoDto();
			dto.setContent(content);
			
			//MemberDao 객체를 이용해서 DB 에 저장
			MemoDao dao=MemoDao.getInstance();
			boolean isSuccess=dao.insert(dto);
			if(isSuccess) {
				JOptionPane.showMessageDialog(this, content+" 내용을 추가했습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "추가 실패");
			}
			//테이블에 목록다시 출력하기
			displayMember();	
		}else if(command.equals("delete")) {
			int selectedIndex=table.getSelectedRow();
			if(selectedIndex==-1) {
				return;
			}
			
			int selection=JOptionPane.showConfirmDialog(this, "선택된 row를 삭제?");
			if(selection != JOptionPane.YES_OPTION) {
				return;
			}
			int num=(int)model.getValueAt(selectedIndex, 0);
			//3. MemberDao 객체를 이용해서 해당 회원의 정보를 삭제한다.
			MemoDao dao=MemoDao.getInstance();
			dao.delete(num);
			//4. table 에 회원목록 다시 출력하기 
			displayMember();
		}
	}
	//현재 테이블 cell을 수정중인지 여부를 저장할 필드 
		boolean isEditing=false;
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			System.out.println("property change!");
			System.out.println(evt.getPropertyName());
			if(evt.getPropertyName().equals("tableCellEditor")) {
				if(isEditing) {//수정중일때 
					//변화된 값을 읽어와서 DB 에 반영한다. 
					//수정된 칼럼에 있는 row  전체의 값을 읽어온다. 
					int selectedIndex=table.getSelectedRow();
					int num=(int)model.getValueAt(selectedIndex, 0);
					String content=(String)model.getValueAt(selectedIndex, 1);
					Date date=(Date)model.getValueAt(selectedIndex, 2);
					//수정할 회원의 정보를 MemberDto 객체에 담고 
					MemoDto dto=new MemoDto(num, content , date);
					//DB에 저장하기 
					MemoDao.getInstance().update(dto);
					isEditing=false;//수정중이 아니라고 표시한다.
				}
				isEditing=true;//수정중이라 표시한다.
			}
		}
}
