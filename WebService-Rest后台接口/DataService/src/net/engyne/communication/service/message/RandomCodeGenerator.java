package net.engyne.communication.service.message;

import java.util.Random;

public class RandomCodeGenerator {

	static Random random=new Random();
	public static String random(int num){
		  //初始化种子
		  String[] str={"0","1","2","3","4","5","6","7","8","9"};
		  int number=str.length;
		  //接收随机字符
		  String text = "";
		  //随机产生4个字符的字符串
		  for(int i=0;i<num;i++){
		   text+=str[random.nextInt(number)];
		  }
		  return text;
		 }
	public static void main(String[] args) {
		System.out.println(random(6));
	}
}
