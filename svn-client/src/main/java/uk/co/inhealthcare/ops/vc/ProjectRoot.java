package uk.co.inhealthcare.ops.vc;

import java.util.Set;

public interface ProjectRoot extends ProjectDirectory {

	Trunk getTrunk();

	Set<Branch> getBranches();

	Set<Tag> getTags();

	String getLocation();

}
