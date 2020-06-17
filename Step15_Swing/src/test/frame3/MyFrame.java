package test.frame3;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	//생성자
	public MyFrame(String title) {
		super(title);
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//레이아웃 메니저를 GridLayout 으로 지정하기 (2행2열)
		setLayout(new GridLayout(2,2));
		
		JButton btn1=new JButton("버튼1");
		btn1.setSize(100,30);
		add(btn1);
		
		JButton btn2=new JButton("버튼2");
		btn2.setSize(100,30);
		add(btn2);
		
		JButton btn3=new JButton("버튼3");
		btn3.setSize(100,30);
		add(btn3);
		
		JButton btn4=new JButton("버튼4");
		btn4.setSize(100,30);
		add(btn4);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyFrame("나의 프레임");
	}
}
	

