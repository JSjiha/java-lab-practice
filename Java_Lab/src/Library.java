public class Library {
	Book[] mybook = new Book[10];
	
	Library(){
		for(int i=0; i<10; i++) {
			mybook[i] = new Book();
			mybook[i].Borrowed = false;
			mybook[i].Returned = true;
		}
	}
	
	void borrowing(int n) {
		if(mybook[n].Borrowed == false) {
			mybook[n].Borrowed = true;
			mybook[n].Returned = false;
			System.out.println("The " + n + "-th book is borrowed.");
		}
		else System.out.println("error: The " + n + "-th book is already borrowed.");
	}
	
	void returning(int n) {
		if(mybook[n].Returned == false) {
			mybook[n].Borrowed = false;
			mybook[n].Returned = true;
			System.out.println("The " + n + "-th book is returned.");
		}
		else System.out.println("error: The " + n + "-th book is already returned.");
	}
	
	public static void main(String[] args) {

	}
	
}

class Book {
		Boolean Borrowed;
		Boolean Returned;
}

