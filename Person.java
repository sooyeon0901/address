package addressbook;

public class Person {
	public String name;
	public int age;
	public String addr;
	public String cellPhone;
	
	public Person(){
		
	}

	public Person(String name, int age, String addr, String cellPhone) {
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.cellPhone = cellPhone;
	}
	
	String get() {
		return String.format("이름:%s, 나이:%s, 주소:%s, 연락처:%s", name,age,addr,cellPhone);
	}
	
	public void print() {
		System.out.println(get());
	}
	
	public int compareTo(Person target) {
		return target.age-age;
	}

	@Override
	public String toString() {
		return get();
	}
	
}//Person
