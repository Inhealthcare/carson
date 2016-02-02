package uk.co.inhealthcare.ops.vc;

import java.io.InputStream;
import java.util.Set;

public interface VersionControlTemplate {

	void testConnection() throws VersionControlClientException;

	Set<ProjectRoot> getProjectRoots() throws VersionControlClientException;

	InputStream getContent(ProjectDirectory dir, String path) throws VersionControlClientException;

}
