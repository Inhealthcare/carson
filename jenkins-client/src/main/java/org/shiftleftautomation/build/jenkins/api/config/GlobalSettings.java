package org.shiftleftautomation.build.jenkins.api.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class GlobalSettings {

	@XmlAttribute(name = "class")
	private String clazz;

	public String getClazz() {
		return clazz;
	}

}
