package test.main;

import test.mypac.Apple;
import test.mypac.Banana;
import test.mypac.FruitBox;
import test.mypac.Orange;

public class MainClass01 {
	public static void main(String[] args) {
		FruitBox<Apple> box1=new FruitBox<Apple>();
		FruitBox<Orange> box2=new FruitBox<Orange>();
		FruitBox<Banana> box3=new FruitBox<Banana>();
		
		//Generic 클래스로 지정한 type 객체를 넣어주어야 한다.
		box1.push(new Apple());
		box2.push(new Orange());
		box3.push(new Banana());
		
		//Generic 클래스로 지정한 type 객체가 리턴된다.
		Apple result1=box1.pull();
		Orange result2=box2.pull();
		Banana result3=box3.pull();
	
	}
}
