package addressbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;


public class AddressBookLogic {

	public static final int MAX_PERSON = 3;
	Map<Character,List<Person>> addressBook = new HashMap<>();
	List<Person> person;
	

	public AddressBookLogic() {
		person = new Vector<Person>();
		addressBook = new HashMap<>();
	}
	
	void printMainMenu() {
		System.out.println("=================== 주소록 메뉴 ====================");
		System.out.println("1.입력  2.출력  3.수정  4.삭제  5.검색  9.종료");
		System.out.println("================================================");
		System.out.println("주소록 메뉴 번호를 입력하세요.");
	}//printMainMenu
	
	int getMenuNumber() {
		Scanner sc = new Scanner(System.in);
		String menuString = sc.nextLine();
		
		return Integer.parseInt(menuString);
	}//getMenuNumber
	
	void seperateMenu(int menuString) {
		switch(menuString) {
			case 1: setPerson();
				break;
			case 2: printPerson();
				break;
			case 3: updatePerson();
				break;
			case 4: deletePerson();
				break;
			case 5: searchPerson();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				break;
			default: System.out.println("없는 번호입니다. 다시 입력해 주세요.");
		}
	}//seperateMenu

	private void setPerson() {
		//문제) 정원이 차지 않음 
		if(person.size() == MAX_PERSON) {
			System.out.println("정원이 찼습니다. 더이상 입력할 수 없습니다.");
			return;
		}
		//문제) 검색에서 마지막 사람 정보만 도출되고 다른 사람들 것은 없다고 나옴 
		//문제) 영어를쳐도 한글명이 아닙니다가 안나옴 - 해결
		
			Scanner sc = new Scanner(System.in);	
			String name;
			char jaeum;
			
			
			while(true) {
				
				System.out.println("이름을 입력하세요.");
				name = sc.nextLine();
				
				jaeum = getFirstCharactor(name);
				if(jaeum == '0') {
					System.out.println("한글명이 아닙니다.");
				} else break;
			}//while
			System.out.println("나이를 입력하세요.");
			int age = Integer.parseInt(sc.nextLine());
			System.out.println("사는 곳을 입력하세요.");
			String addr = sc.nextLine();
			System.out.println("전화번호를 입력하세요.");
			String cellPhone = sc.nextLine();
			
			if(!addressBook.containsKey(jaeum)) {
				person = new Vector<>();
			} else {
				person = addressBook.get(jaeum);
			}
			person.add(new Person(name, age, addr, cellPhone));
			addressBook.put(jaeum, person);
			System.out.println(addressBook.toString());
			System.out.println(person.toString());
		
	}//setPerson
	
	public char getFirstCharactor(String name) {
		char[] jaeum = name.trim().toCharArray();
		char[] startChar = {
				'가','나','다','라','마','바','사','아','자','차','카','타','파','하'};
		char[] endChar = {
				'낗','닣','띻','맇','밓','삫','앃','잏','찧','칳','킿','팋','핗','힣'};
		char[] returnJaeum = {
				'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		for(int i=0; i<startChar.length; i++) {
			if(jaeum[0] >= startChar[i] && jaeum[0] <= endChar[i]) {
				return returnJaeum[i];
			}//if
		}//for
		return '0';
		
	}//getFirstCharactor

	private void printPerson() {
		System.out.println("[주소록 출력]");
		Set<Character> keys = addressBook.keySet();
			for(Character key:keys) {
				System.out.println(String.format("[\'%c\'(으)로 시작하는 명단]",key));
				List<Person> values = addressBook.get(key);
				for(Person p:values) {
					System.out.println(p.get());
				}
			}//for
		
	}//printPerson
	
	
	private Person findPersonByName(String title) {
		System.out.println(title+"할 사람의 이름을 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine().trim();
		
		Set<Character> keys = addressBook.keySet();
		for(Character key:keys) {
			List<Person> person = addressBook.get(key);
			for(Person p:person) {
				if(p.name.equals(name)) {
					return p;
				}
			}
		}//마지막값만 나왔었는데  keyset 사용해서 모든 값이 저장된  address불러오니까 다 도출됨..
		
		System.out.println("\'"+name+"\'(으)로 검색된 정보가 없습니다.");
		return null;
	}//findPersonByName

	
	private void searchPerson() {
		Person findPerson = findPersonByName("검색");
		if(findPerson != null) {
			System.out.printf("[%s(으)로 검색한 결과]%n", findPerson.name);
			findPerson.print();
		}
		
	}//searchPerson

	
	private void updatePerson() {
		Person findPerson = findPersonByName("수정");
		
	}//updatePerson
	
	
	private void deletePerson() {
		//문제) 삭제는 두번쨰 값이 없는 값으로 나옴? 
		Person findPerson = findPersonByName("삭제");
		
		if(findPerson != null) {
			for(Person p:person) {
				if(findPerson.equals(p)) {
					
					person.remove(p);
					
					System.out.printf("[%s(이)가 삭제되었습니다]%n",findPerson.name);
					break;
				}
			}
		}
	}//deletePerson

}//AddressBookLogic
