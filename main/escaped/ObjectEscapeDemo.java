package escaped;

public class ObjectEscapeDemo {

	public static void main(String[] args) {
		
		UnsafeStates stats = new UnsafeStates();
		System.out.println(stats);
		String modified = "CN";
		stats.getStats()[0] = modified;
		System.out.println(stats);
	}
}
