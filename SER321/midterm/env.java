public class env{
	public static void main(String[] args) {
		for(String s: args) {
			String val = System.getenv(s);
			if(val != null)
				System.out.format("%s=%s%n", s, val);
			else
				System.out.format("%s is not assigned.%n", s);
		}
	}
}
