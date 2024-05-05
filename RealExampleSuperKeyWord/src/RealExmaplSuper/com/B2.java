package RealExmaplSuper.com;

interface  datacheck{
	
 static void run() {
	 System.out.println("Hello World");
 }
}
abstract class data{
	abstract void run() ;
}
 class Operation extends data implements datacheck {
	public void run() {
	System.out.println("Running 1");
	}
	public static void main(String[] args) {
		Operation o=new Operation();
		o.run();
		
		datacheck.run();
	
		

	}
}