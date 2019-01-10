package application;

public enum MessageBoxButton {
	AbortRetryIgnore("Przerwij", "Ponów", "Ignoruj"), OK("OK"), OKCancel("OK", "Anuluj"), RetryCancel("Ponów",
			"Anuluj"), YesNo("Tak", "Nie"), YesNoCancel("Tak", "Nie", "Anuluj");

	private String[] text = new String[3];
	private int count;

	MessageBoxButton(String... msg) {
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
