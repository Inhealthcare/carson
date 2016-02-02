package uk.co.inhealthcare.ops.svn;

import uk.co.inhealthcare.ops.vc.ProjectDirectory;
import uk.co.inhealthcare.ops.vc.Tag;

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
