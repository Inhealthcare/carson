package uk.co.inhealthcare.ops.svn;

public class SVNLocationBuilder {

	private String url;
	private String username;
	private String password;

	public static SVNLocationBuilder create() {
		return new SVNLocationBuilder();
	}

	public SVNLocationBuilder url(String url) {
		this.url = url;
		return this;
	}

	public SVNLocationBuilder credentials(String username, String password) {
		this.username = username;
		this.password = password;
		return this;
	}

	public SVNLocation build() {
		return new SVNLocation(url, username, password);
	}

}
