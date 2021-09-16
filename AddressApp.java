package addressbook;

public class AddressApp {

	public static void main(String[] args) {
		AddressBookLogic logic = new AddressBookLogic();
		
		while(true) {
			logic.printMainMenu();
			int menuString = logic.getMenuNumber();
			logic.seperateMenu(menuString);
		}//while
	}

}
