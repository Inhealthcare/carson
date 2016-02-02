package org.shiftleftautomation.svn;

import org.shiftleftautomation.vc.Branch;
import org.shiftleftautomation.vc.ProjectDirectory;

public class SVNBranch implements Branch {

	private String name;
	private ProjectDirectory parent;

	public SVNBranch(ProjectDirectory parent, String name) {
		this.parent = parent;
		this.name = name;
	}

	@Override
	public String getName() {
		return "branches/" + name;
	}

	@Override
	public String getPath() {
		return parent.getPath() + "/" + getName();
	}

}
