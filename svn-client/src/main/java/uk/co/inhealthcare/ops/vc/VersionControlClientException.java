package uk.co.inhealthcare.ops.vc;

public class VersionControlClientException extends Exception {

	private static final long serialVersionUID = -1163445441058889679L;

	public VersionControlClientException() {
		super();
	}

	public VersionControlClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VersionControlClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public VersionControlClientException(String message) {
		super(message);
	}

	public VersionControlClientException(Throwable cause) {
		super(cause);
	}

}
