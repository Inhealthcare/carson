package com.carson.webapp.projects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.carson.webapp.AbstractEntity;

@Entity
public class SourceControl extends AbstractEntity {

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "url", column = @Column(name = "SVN_URL") ) })
	private SvnRepository svnRepository;

	public SvnRepository getSvnRepository() {
		return svnRepository;
	}

	public void setSvnRepository(SvnRepository svnRepository) {
		this.svnRepository = svnRepository;
	}

}
