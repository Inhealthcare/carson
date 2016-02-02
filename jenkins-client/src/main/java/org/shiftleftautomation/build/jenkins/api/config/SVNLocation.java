package org.shiftleftautomation.build.jenkins.api.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SVNLocation {

	private String remote;
	private String local;
	private String depthOption;
	private boolean ignoreExternalOptions;
	
	public String getRemote() {
		return remote;
	}
	
}
