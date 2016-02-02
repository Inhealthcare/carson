package org.shiftleftautomation.build.jenkins.api.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

	@XmlAttribute(name = "plugin")
	private String plugin;	
	private List<Action> actions;	
	private Description description;
	private boolean keepDependencies;
	private Properties properties;
	private Scm scm;
	private boolean canRoam;
	private boolean disabled;
	private boolean blockBuildWhenDownstreamBuilding;
	private boolean blockBuildWhenUpstreamBuilding;
	private List<Trigger> triggers;
	private boolean concurrentBuild;
	private String goals;
	private boolean aggregatorStyleBuild;
	private boolean incrementalBuild;
	private boolean ignoreUpstremChanges;
	private boolean archivingDisabled;
	private boolean siteArchivingDisabled;
	private boolean fingerprintingDisabled;
	private boolean resolveDependencies;
	private boolean processPlugins;
	private int mavenValidationLevel;
	private boolean runHeadless;
	private boolean disableTriggerDownstreamProjects;
	private boolean blockTriggerWhenBuilding;
	private Settings settings;
	private GlobalSettings globalSettings;
	private List<Reporter> reports;
	private List<Publisher> publishers;
	private List<BuildWrapper> buildWrappers;
	private List<PreBuilder> prebuilders;
	private List<PostBuilder> postbuilders;
	private List<RunPostStepsIfResult> runPostStepsIfResult;
	
	public Settings getSettings() {
		return settings;
	}
	
	public GlobalSettings getGlobalSettings() {
		return globalSettings;
	}
	
	public String getPlugin() {
		return plugin;
	}
	
	public Scm getScm() {
		return scm;
	}

	public String serialize() {
		return "<maven2-moduleset plugin=\"maven-plugin@2.7.1\"><actions/><description></description><keepDependencies>false</keepDependencies><properties/><scm class=\"hudson.scm.NullSCM\"/><canRoam>true</canRoam><disabled>false</disabled><blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding><blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding><triggers/><concurrentBuild>false</concurrentBuild><goals>clean install</goals><aggregatorStyleBuild>true</aggregatorStyleBuild><incrementalBuild>false</incrementalBuild><ignoreUpstremChanges>false</ignoreUpstremChanges><archivingDisabled>false</archivingDisabled><siteArchivingDisabled>false</siteArchivingDisabled><fingerprintingDisabled>false</fingerprintingDisabled><resolveDependencies>false</resolveDependencies><processPlugins>false</processPlugins><mavenValidationLevel>-1</mavenValidationLevel><runHeadless>false</runHeadless><disableTriggerDownstreamProjects>false</disableTriggerDownstreamProjects><blockTriggerWhenBuilding>true</blockTriggerWhenBuilding><settings class=\"jenkins.mvn.DefaultSettingsProvider\"/><globalSettings class=\"jenkins.mvn.DefaultGlobalSettingsProvider\"/><reporters/><publishers/><buildWrappers/><prebuilders/><postbuilders/><runPostStepsIfResult><name>FAILURE</name><ordinal>2</ordinal><color>RED</color><completeBuild>true</completeBuild></runPostStepsIfResult></maven2-moduleset>";
	}

}
