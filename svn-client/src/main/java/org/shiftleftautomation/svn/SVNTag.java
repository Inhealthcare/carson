package org.shiftleftautomation.svn;

import org.shiftleftautomation.vc.ProjectDirectory;
import org.shiftleftautomation.vc.Tag;

public class SVNTag implements Tag {

	private String name;
	private ProjectDirectory parent;

	public SVNTag(ProjectDirectory parent, String name) {
		this.parent = parent;
		this.name = name;
	}

	@Override
	public String getName() {
		return "tags/" + name;
	}

	@Override
	public String getPath() {
		return parent.getPath() + "/" + getName();
	}

}
