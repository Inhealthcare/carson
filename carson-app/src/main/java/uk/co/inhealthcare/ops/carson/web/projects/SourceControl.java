package uk.co.inhealthcare.ops.carson.web.projects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import uk.co.inhealthcare.ops.carson.web.AbstractEntity;

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
