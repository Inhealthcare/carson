package org.jenkins.client.api.job;

import java.util.List;

public class JobState {

	private List<Action> actions;
	private String description;
	private String displayName;
	private String displayNameOrNull;
	private String name;
	private String url;
	private boolean buildable;
	private List<Build> builds;
	private String color;
	private Build firstBuild;
	private List<HealthReport> healthReport;
	private boolean inQueue;
	private boolean keepDependencies;
	private Build lastBuild;
	private Build lastCompletedBuild;
	private Build lastFailedBuild;
	private Build lastStableBuild;
	private Build lastSuccessfulBuild;
	private Build lastUnstableBuild;
	private Build lastUnsuccessfulBuild;
	private int nextBuildNumber;
	private List<Property> property;
	private Build queueItem;
	private boolean concurrentBuild;
	private List<Project> downstreamProjects;
	private Scm scm;
	private List<Project> upstreamProjects;
	private List<Module> modules;

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayNameOrNull() {
		return displayNameOrNull;
	}

	public void setDisplayNameOrNull(String displayNameOrNull) {
		this.displayNameOrNull = displayNameOrNull;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isBuildable() {
		return buildable;
	}

	public void setBuildable(boolean buildable) {
		this.buildable = buildable;
	}

	public List<Build> getBuilds() {
		return builds;
	}

	public void setBuilds(List<Build> builds) {
		this.builds = builds;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Build getFirstBuild() {
		return firstBuild;
	}

	public void setFirstBuild(Build firstBuild) {
		this.firstBuild = firstBuild;
	}

	public List<HealthReport> getHealthReport() {
		return healthReport;
	}

	public void setHealthReport(List<HealthReport> healthReport) {
		this.healthReport = healthReport;
	}

	public boolean isInQueue() {
		return inQueue;
	}

	public void setInQueue(boolean inQueue) {
		this.inQueue = inQueue;
	}

	public boolean isKeepDependencies() {
		return keepDependencies;
	}

	public void setKeepDependencies(boolean keepDependencies) {
		this.keepDependencies = keepDependencies;
	}

	public Build getLastBuild() {
		return lastBuild;
	}

	public void setLastBuild(Build lastBuild) {
		this.lastBuild = lastBuild;
	}

	public Build getLastCompletedBuild() {
		return lastCompletedBuild;
	}

	public void setLastCompletedBuild(Build lastCompletedBuild) {
		this.lastCompletedBuild = lastCompletedBuild;
	}

	public Build getLastFailedBuild() {
		return lastFailedBuild;
	}

	public void setLastFailedBuild(Build lastFailedBuild) {
		this.lastFailedBuild = lastFailedBuild;
	}

	public Build getLastStableBuild() {
		return lastStableBuild;
	}

	public void setLastStableBuild(Build lastStableBuild) {
		this.lastStableBuild = lastStableBuild;
	}

	public Build getLastSuccessfulBuild() {
		return lastSuccessfulBuild;
	}

	public void setLastSuccessfulBuild(Build lastSuccessfulBuild) {
		this.lastSuccessfulBuild = lastSuccessfulBuild;
	}

	public Build getLastUnstableBuild() {
		return lastUnstableBuild;
	}

	public void setLastUnstableBuild(Build lastUnstableBuild) {
		this.lastUnstableBuild = lastUnstableBuild;
	}

	public Build getLastUnsuccessfulBuild() {
		return lastUnsuccessfulBuild;
	}

	public void setLastUnsuccessfulBuild(Build lastUnsuccessfulBuild) {
		this.lastUnsuccessfulBuild = lastUnsuccessfulBuild;
	}

	public int getNextBuildNumber() {
		return nextBuildNumber;
	}

	public void setNextBuildNumber(int nextBuildNumber) {
		this.nextBuildNumber = nextBuildNumber;
	}

	public List<Property> getProperty() {
		return property;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}

	public Build getQueueItem() {
		return queueItem;
	}

	public void setQueueItem(Build queueItem) {
		this.queueItem = queueItem;
	}

	public boolean isConcurrentBuild() {
		return concurrentBuild;
	}

	public void setConcurrentBuild(boolean concurrentBuild) {
		this.concurrentBuild = concurrentBuild;
	}

	public List<Project> getDownstreamProjects() {
		return downstreamProjects;
	}

	public void setDownstreamProjects(List<Project> downstreamProjects) {
		this.downstreamProjects = downstreamProjects;
	}

	public Scm getScm() {
		return scm;
	}

	public void setScm(Scm scm) {
		this.scm = scm;
	}

	public List<Project> getUpstreamProjects() {
		return upstreamProjects;
	}

	public void setUpstreamProjects(List<Project> upstreamProjects) {
		this.upstreamProjects = upstreamProjects;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

}
