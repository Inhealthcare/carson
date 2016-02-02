package org.shiftleftautomation.svn;

public class SVNLocation {

	private String url;
	private String username;
	private String password;

	public SVNLocation(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "SVNLocation [url=" + url + ", username=" + username + ", password=*******]";
	}

}
