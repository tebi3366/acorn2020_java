package test.mypac;

@FunctionalInterface //메소드 하나만 정의하게 만듬 @fun 컨트롤 스페이스
public interface Calculator {
	//인자로 전달되는 두 실수값을 연산해서 결과를 리턴해주는 메소드 
	public double exec(double a, double b);
}

