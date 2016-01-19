package org.jenkins.client;

import java.util.List;

import org.w3c.dom.Element;

public class Maven2ModuleSet extends Project {

	private String goals;
	private boolean aggregatorStyleBuild;
	private boolean incrementalBuild;
	private boolean ignoreUpstremChanges;
	private boolean archivingDisabled;
	private boolean siteArchivingDisabled;
	private boolean fingerprintingDisabled;
	private boolean resolveDependencies;
	private boolean processPlugins;
	private boolean mavenValidationLevel;
	private boolean runHeadless;
	private boolean disableTriggerDownstreamProjects;
	private boolean blockTriggerWhenBuilding;
	private Element settings;
	private Element globalSettings;
	private List<Element> reporters;
	private List<Element> buildWrappers;
	private List<Element> prebuilders;
	private List<Element> postbuilders;
	private Element runPostStepsIfResult;
	
}
