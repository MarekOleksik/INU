package application;

public enum MessageBoxIcon { 
	Information("Informacja"), Warning("Ostrze¿enie"), Alert("Alarm"), CriticalError("B³¹d krytyczny");

	private String[] text = new String[3];
	private int count;

	MessageBoxIcon(String... msg) {
		for (int i = 0; i < msg.length; ++i)
			text[i] = msg[i];
		count = msg.length;
	}
	
	@Override
	public String toString() {
		return text[0];
	}
	
	public String getText(int i) {
		return text[i];
	}
	
	public int getCount() {
		return count;
	}

	
}
