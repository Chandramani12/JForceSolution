package com.info.httpServer;

public class Bike {
	
	void run() {
		System.out.println("Bike is running");
	}

}
 class Vichel extends Bike{
	 void run() {
		 System.out.println("Vichel is running");
	 }
	 public static void main(String[] args) {
		 Bike obj=new Bike();
		 obj.run();
		 obj.run();
		 
		
	}
	 
	
}
