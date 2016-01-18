package org.jenkins.client;

public class JobXmlBuilder {

	private JobConfig config;

	public JobXmlBuilder(JobConfig config) {
		this.config = config;
	}

	public String toXml() {
		return "<maven2-moduleset plugin=\"maven-plugin@2.7.1\"><actions/><description></description><keepDependencies>false</keepDependencies><properties/><scm class=\"hudson.scm.NullSCM\"/><canRoam>true</canRoam><disabled>false</disabled><blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding><blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding><triggers/><concurrentBuild>false</concurrentBuild><goals>clean install</goals><aggregatorStyleBuild>true</aggregatorStyleBuild><incrementalBuild>false</incrementalBuild><ignoreUpstremChanges>false</ignoreUpstremChanges><archivingDisabled>false</archivingDisabled><siteArchivingDisabled>false</siteArchivingDisabled><fingerprintingDisabled>false</fingerprintingDisabled><resolveDependencies>false</resolveDependencies><processPlugins>false</processPlugins><mavenValidationLevel>-1</mavenValidationLevel><runHeadless>false</runHeadless><disableTriggerDownstreamProjects>false</disableTriggerDownstreamProjects><blockTriggerWhenBuilding>true</blockTriggerWhenBuilding><settings class=\"jenkins.mvn.DefaultSettingsProvider\"/><globalSettings class=\"jenkins.mvn.DefaultGlobalSettingsProvider\"/><reporters/><publishers/><buildWrappers/><prebuilders/><postbuilders/><runPostStepsIfResult><name>FAILURE</name><ordinal>2</ordinal><color>RED</color><completeBuild>true</completeBuild></runPostStepsIfResult></maven2-moduleset>";
	}

}