package uk.co.inhealthcare.ops.build.jenkins.api.state;

import java.util.List;

public class JenkinsState {

	private List<Label> assignedLabels;
	private Mode mode;
	private String nodeDescription;
	private String nodeName;
	private int numExecutors;
	private String description;
	private List<JobSummary> jobs;
	private Load overallLoad;
	private View primaryView;
	private boolean quietingDown;
	private int slaveAgentPort;
	private Load unlabeledLoad;
	private boolean useCrumbs;
	private boolean useSecurity;
	private List<View> views;

	public List<Label> getAssignedLabels() {
		return assignedLabels;
	}

	public void setAssignedLabels(List<Label> assignedLabels) {
		this.assignedLabels = assignedLabels;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public String getNodeDescription() {
		return nodeDescription;
	}

	public void setNodeDescription(String nodeDescription) {
		this.nodeDescription = nodeDescription;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getNumExecutors() {
		return numExecutors;
	}

	public void setNumExecutors(int numExecutors) {
		this.numExecutors = numExecutors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<JobSummary> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobSummary> jobs) {
		this.jobs = jobs;
	}

	public Load getOverallLoad() {
		return overallLoad;
	}

	public void setOverallLoad(Load overallLoad) {
		this.overallLoad = overallLoad;
	}

	public View getPrimaryView() {
		return primaryView;
	}

	public void setPrimaryView(View primaryView) {
		this.primaryView = primaryView;
	}

	public boolean isQuietingDown() {
		return quietingDown;
	}

	public void setQuietingDown(boolean quietingDown) {
		this.quietingDown = quietingDown;
	}

	public int getSlaveAgentPort() {
		return slaveAgentPort;
	}

	public void setSlaveAgentPort(int slaveAgentPort) {
		this.slaveAgentPort = slaveAgentPort;
	}

	public Load getUnlabeledLoad() {
		return unlabeledLoad;
	}

	public void setUnlabeledLoad(Load unlabeledLoad) {
		this.unlabeledLoad = unlabeledLoad;
	}

	public boolean isUseCrumbs() {
		return useCrumbs;
	}

	public void setUseCrumbs(boolean useCrumbs) {
		this.useCrumbs = useCrumbs;
	}

	public boolean isUseSecurity() {
		return useSecurity;
	}

	public void setUseSecurity(boolean useSecurity) {
		this.useSecurity = useSecurity;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

}
