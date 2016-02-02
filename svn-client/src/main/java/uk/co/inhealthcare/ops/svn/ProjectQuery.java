package uk.co.inhealthcare.ops.svn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.io.SVNRepository;

public class ProjectQuery {

	private static final int DEFAULT_HEAD_REVISION = -1;

	private static final String PATH_SEPARATOR = "/";

	private static final String ROOT_PATH = "/";

	private static final Logger logger = LoggerFactory.getLogger(ProjectQuery.class);

	private SVNRepository repository;

	public ProjectQuery(SVNRepository repository) {
		this.repository = repository;
	}

	public List<SVNProjectRoot> listProjectRoots() throws SVNException {

		logger.debug("Querying for project roots");

		List<SVNProjectRoot> list = new ArrayList<>();

		findProjectRoots("ROOT", ROOT_PATH, list);

		logger.debug("Found {} project roots", list.size());

		return list;

	}

	public void findProjectRoots(String name, String path, List<SVNProjectRoot> list) throws SVNException {

		Collection<SVNDirEntry> entries = new ArrayList<>();
		SVNDirEntry directory = repository.getDir(path, DEFAULT_HEAD_REVISION, false,
				(Collection<SVNDirEntry>) entries);
		logger.debug("Inspecting directory " + path);

		if (isProjectRoot(directory, entries)) {
			logger.debug("Found project root at {} now looking for branches and tags", path);

			Set<SVNDirEntry> branches = new HashSet<>();
			repository.getDir(path + "/branches", -1, false, branches);
			Set<String> branchNames = branches.stream().map(d -> d.getName()).collect(Collectors.toSet());
			logger.debug("Found {} branches: {}", branchNames.size(), branchNames);

			Set<SVNDirEntry> tags = new HashSet<>();
			repository.getDir(path + "/tags", -1, false, tags);
			Set<String> tagNames = tags.stream().map(d -> d.getName()).collect(Collectors.toSet());
			logger.debug("Found {} tags: {}", tagNames.size(), tagNames);

			list.add(new SVNProjectRoot(name, path, directory.getURL().toDecodedString(), branchNames, tagNames));
			return;
		}

		for (SVNDirEntry entry : entries) {
			if (entry.getKind() == SVNNodeKind.DIR) {
				String dirPath = isRootPath(path) ? pathRelativeToRoot(path, entry) : pathRelativeToParent(path, entry);
				logger.debug("Moving into directory " + dirPath);
				findProjectRoots(entry.getName(), dirPath, list);
			}
		}

	}

	private String pathRelativeToParent(String path, SVNDirEntry entry) {
		return path + PATH_SEPARATOR + entry.getName();
	}

	private String pathRelativeToRoot(String path, SVNDirEntry entry) {
		return path + entry.getName();
	}

	private boolean isRootPath(String path) {
		return ROOT_PATH.equals(path);
	}

	private static boolean isProjectRoot(SVNDirEntry directory, Collection<SVNDirEntry> entries) {

		List<String> projectFolders = new ArrayList<>(Arrays.asList("trunk", "branches", "tags"));
		for (SVNDirEntry entry : entries) {
			if (!projectFolders.contains(entry.getName())) {
				return false;
			} else {
				projectFolders.remove(entry.getName());
			}
		}

		if (!projectFolders.isEmpty()) {
			return false;
		}

		return true;
	}

}
