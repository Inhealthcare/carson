package org.jenkins.client;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

@XmlRootElement
public class Project {
	
	private List<Element> actions;
	private String description;
	private boolean keepDependencies;
	private List<Element> properties;
	private String scm;
	private boolean canRoam;
	private boolean disabled;
	private boolean blockBuildWhenDownstreamBuilding;
	private boolean blockBuildWhenUpstreamBuilding;
	private List<Element> triggers;
	private boolean concurrentBuild;
	private List<Element> builders;
	private List<Element> publishers;
	private List<Element> buildWrappers;

}
