package escaped;

public class UnsafeStates {

	private String[] stats = new String[] {"Open", "Closed"};
	
	public String[] getStats() {
		return stats;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i< stats.length; i++) {
			builder.append(stats[i]).append(",");
		}
		return builder.toString();
	}
}
