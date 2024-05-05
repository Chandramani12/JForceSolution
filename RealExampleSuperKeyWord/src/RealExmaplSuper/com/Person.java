package RealExmaplSuper.com;

public class Person {
	
		int id;
		String Name;
		Person(int id,String name){
			this.id=id;
			this.Name=name;
		}
}

class Employee extends Person{
	float salary;
	Employee(int id,String name,float salary){
		super(id,name);
		this.salary=salary;
	}
	void display() {
		System.out.println(id +""+Name+""+salary);
	}
	public static void main(String[] args) {
		Employee obj=new Employee(1 ," "+ "Chandramani" + " ", 20000);
		obj.display();
	}
}
