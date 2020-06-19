package example4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientMain extends JFrame 
				implements ActionListener,KeyListener{
	//필드
	JTextField tf_msg;
	//서버와 연결된 Socket 객체의 참조값을 담을 필드
	Socket socket;
	BufferedWriter bw;
	JTextArea area;
	//생성자
	public ClientMain() {
		//서버에 소켓 접속을 한다.
		try {//접속이 성공되면 Socket 객체의 참ㅈ값이 반환된다.
			//반환되는 객체의 참조값을 필드에 저장해 놓는다.
			socket=new Socket("192.168.0.30", 5000);
			//서버에 문자열 출력할
			//BufferedWriter 객체의 참조값을 얻어내서 필드에 저장해 놓는다.
			OutputStream os=socket.getOutputStream();
			OutputStreamWriter osw=new OutputStreamWriter(os);
			bw=new BufferedWriter(osw);
			//서버로 부터 메세지를 받을 스레드도 시작을 시킨다.
			new ClientThread().start();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		//레이아웃을 BorderLayout 으로 지정하기 
		setLayout(new BorderLayout());
		
		//페널
		JPanel panel=new JPanel();
		panel.setBackground(Color.YELLOW);
		//입력창
		tf_msg=new JTextField(10);
		//버튼
		JButton sendBtn=new JButton("전송");
		sendBtn.setActionCommand("send");
		//페널에 입력창과 버튼을 추가
		panel.add(tf_msg);
		panel.add(sendBtn);
		//프레임의 아래쪽에 페널 배치하기
		add(panel, BorderLayout.SOUTH);
		
		//버튼에 리스너 등록
		sendBtn.addActionListener(this);
		
		area=new JTextArea();
		//문자열 출력 전용으로 사용하기 위해 편집 불가능하도록 설정
		area.setEditable(false);
		//배경색 
		area.setBackground(Color.pink);
		//스크롤 가능하도록
		JScrollPane scroll=new JScrollPane(area,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//프레임의 가운데에 배치하기
		add(scroll, BorderLayout.CENTER);
		
		//엔터키로 메세지 전송 가능하게 하기 위해
		tf_msg.addKeyListener(this);
		
	}
	
	
	public static void main(String[] args) {
		//프레임 객체 생성
		ClientMain f=new ClientMain();
		f.setTitle("쳇팅창");
		f.setBounds(100, 100, 500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}









