package org.shiftleftautomation.build.jenkins.api.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Location {

	@XmlElement(name="hudson.scm.SubversionSCM_-ModuleLocation")
	private SVNLocation svnLocation;
	
	public SVNLocation getSvnLocation() {
		return svnLocation;
	}
	
}
