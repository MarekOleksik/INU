package application;

public enum MessageBoxIcon {
	Information("application/images/StatusInformation_64x.png"), Warning(
			"application/images/StatusWarning_64x.png"), Alert("application/images/StatusAlert_64x.png"), CriticalError(
					"application/images/StatusCriticalError_64x.png");

	private String text;

	MessageBoxIcon(String msg) {
		text = msg;
	}

	@Override
	public String toString() {
		return text;
	}

}
