package org.shiftleftautomation.svn;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.shiftleftautomation.vc.Branch;
import org.shiftleftautomation.vc.ProjectRoot;
import org.shiftleftautomation.vc.Tag;
import org.shiftleftautomation.vc.Trunk;

public class SVNProjectRoot implements ProjectRoot {

	private String name;
	private String path;
	private String location;
	private SVNTrunk trunk;
	private Set<Branch> branches = new HashSet<>();
	private Set<Tag> tags = new HashSet<>();

	public SVNProjectRoot(String name, String path, String location, Set<String> branchNames, Set<String> tagNames) {
		this.name = name;
		this.path = path;
		this.location = location;
		trunk = new SVNTrunk(this);
		if (branchNames != null) {
			branches.addAll(branchNames.stream().map(s -> new SVNBranch(this, s)).collect(Collectors.toSet()));
		}
		if (tagNames != null) {
			tags.addAll(tagNames.stream().map(s -> new SVNTag(this, s)).collect(Collectors.toSet()));
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public Trunk getTrunk() {
		return trunk;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public Set<Branch> getBranches() {
		return branches;
	}

	@Override
	public Set<Tag> getTags() {
		return tags;
	}

}
