package org.shiftleftautomation.svn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.shiftleftautomation.vc.ProjectDirectory;
import org.shiftleftautomation.vc.ProjectRoot;
import org.shiftleftautomation.vc.VersionControlClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNTemplate implements SVNOperations, InitializingBean {

	public interface ProjectProcessor {

		void process(SVNRepository repository, String entryPath, SVNDirEntry entry) throws Exception;

	}

	private static final Logger logger = LoggerFactory.getLogger(SVNTemplate.class);

	private SVNLocation location;

	public SVNTemplate(SVNLocation location) {
		this.location = location;
		afterPropertiesSet();
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(location, "Must declare a SVNLocation");
	}

	@Override
	public void testConnection() throws VersionControlClientException {

		try {

			// check can connect to the remote repository
			SVNRepository repository = getRepository();
			repository.closeSession();

		} catch (Exception e) {
			throw new VersionControlClientException(e);
		}

	}

	private SVNRepository getRepository() throws VersionControlClientException {

		logger.info("Connecting to location: {}", location);

		SVNRepository repository = null;

		try {

			String url = location.getUrl();
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
			ISVNAuthenticationManager authenticationManager = SVNWCUtil
					.createDefaultAuthenticationManager(location.getUsername(), location.getPassword());
			repository.setAuthenticationManager(authenticationManager);

			repository.testConnection();

			logger.debug("Root: {}", repository.getRepositoryRoot(true));
			logger.debug("UUID: {}", repository.getRepositoryUUID(true));

			checkLocationIsDirectory(repository, url);

			long latestRevision = repository.getLatestRevision();
			logger.info("Latest revision: " + latestRevision);

			return repository;

		} catch (SVNException e) {
			if (repository != null) {
				repository.closeSession();
			}
			logger.warn("Could not create Repository", e);
			throw new VersionControlClientException(e);
		}

	}

	private void checkLocationIsDirectory(SVNRepository repository, String url)
			throws SVNException, VersionControlClientException {
		SVNNodeKind nodeKind = repository.checkPath("", -1);
		if (nodeKind == SVNNodeKind.NONE) {
			throw new VersionControlClientException("Could not find entry at " + url);
		} else if (nodeKind == SVNNodeKind.FILE) {
			throw new VersionControlClientException("Expected to find directory but was file at " + url);
		}
	}

	@Override
	public Set<ProjectRoot> getProjectRoots() throws VersionControlClientException {

		try {

			SVNRepository repository = getRepository();

			ProjectQuery projectRepository = new ProjectQuery(repository);

			List<SVNProjectRoot> roots = projectRepository.listProjectRoots();

			repository.closeSession();

			return new HashSet<>(roots);

		} catch (Exception e) {
			throw new VersionControlClientException(e);
		}

	}

	@Override
	public InputStream getContent(ProjectDirectory dir, String path) throws VersionControlClientException {

		try {

			String url = dir.getPath() + path;

			logger.debug("Getting contents for {}", url);

			SVNRepository repository = getRepository();

			SVNNodeKind nodeKind = repository.checkPath(url, -1);

			if (nodeKind == SVNNodeKind.NONE) {
				throw new VersionControlClientException("Could not find entry at " + url);
			} else if (nodeKind == SVNNodeKind.DIR) {
				throw new VersionControlClientException("Expected to find file but was directory at " + url);
			}

			SVNProperties fileProperties = new SVNProperties();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			repository.getFile(url, -1, fileProperties, baos);

			return new ByteArrayInputStream(baos.toByteArray());

		} catch (Exception e) {
			throw new VersionControlClientException(e);
		}

	}

}
