package uk.co.inhealthcare.ops.build.jenkins;

public class JenkinsClientException extends Exception {

	private static final long serialVersionUID = -4987473504655761811L;

	public JenkinsClientException() {
		super();
	}

	public JenkinsClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JenkinsClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public JenkinsClientException(String message) {
		super(message);
	}

	public JenkinsClientException(Throwable cause) {
		super(cause);
	}

}
