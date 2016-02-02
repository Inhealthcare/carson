package uk.co.inhealthcare.ops.svn;

import uk.co.inhealthcare.ops.vc.ProjectDirectory;
import uk.co.inhealthcare.ops.vc.Trunk;

public class SVNTrunk implements Trunk {

	private static final String TRUNK = "trunk";

	private ProjectDirectory parent;

	public SVNTrunk(ProjectDirectory parent) {
		this.parent = parent;
	}

	@Override
	public String getName() {
		return TRUNK;
	}

	@Override
	public String getPath() {
		return parent.getPath() + "/" + getName();
	}

}
