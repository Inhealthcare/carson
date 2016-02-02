package uk.co.inhealthcare.ops.svn;

import uk.co.inhealthcare.ops.vc.Branch;
import uk.co.inhealthcare.ops.vc.ProjectDirectory;

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
