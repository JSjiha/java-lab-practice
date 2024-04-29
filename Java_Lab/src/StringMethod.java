public class StringMethod{
	
	// Returns the string created by adding 's2' after position 'index' of 's1'.
	static String addString (String s1, int index, String s2) {
		String first_string = s1.substring(0, index+1);
		String last_string = s1.substring(index+1, s1.length());
		String cc_string = first_string.concat(s2).concat(last_string);
		return cc_string;
	}
	
	// Returns reversed string of 's'
	static String reverse (String s) {
		String rev_string = s;
		for(int i=s.length(); i>=1; i--)
			rev_string = rev_string.concat(s.substring(i-1, i));
		rev_string = rev_string.substring(s.length());
		return rev_string;
	}
	
	// Returns a string with all 's2's removed from 's1'
	static String removeString (String s1, String s2) {
		String rem = s1;
		for(int i=0; i<s1.split(s2).length; i++)
			rem = rem.concat(s1.split(s2)[i]);
		rem = rem.substring(s1.length());
		return rem;
	}
	
	public static void main(String[] args) {
		System.out.println(addString("0123456", 3, "-"));
		System.out.println(reverse("abc"));
		System.out.println(removeString("01001000", "00"));
		
	}
}
