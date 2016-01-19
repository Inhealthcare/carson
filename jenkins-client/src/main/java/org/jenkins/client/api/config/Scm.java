package org.jenkins.client.api.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class Scm {

	private List<Location> locations;
	private List<Region> excludedRegions;
	private List<Region> includedRegions;
	private List<User> excludedUsers;
	private Revprop excludedRevprop;
	private List<CommitMessage> excludedCommitMessages;
	private WorkspaceUpdater workspaceUpdater;
	private boolean ignoreDirPropChanges;
	private boolean filterChangeLog; 
	
	public List<Location> getLocations() {
		return locations;
	}
	
}
