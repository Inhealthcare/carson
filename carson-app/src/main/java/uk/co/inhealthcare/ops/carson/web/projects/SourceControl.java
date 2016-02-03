package uk.co.inhealthcare.ops.carson.web.projects;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import uk.co.inhealthcare.ops.carson.web.AbstractEntity;

@Entity
public class SourceControl extends AbstractEntity {

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "url", column = @Column(name = "SVN_URL") ) })
	private SvnRepository svnRepository;

	private String trunk;

	@ElementCollection
	private Set<String> branches;

	@ElementCollection
	private Set<String> tags;

	public SvnRepository getSvnRepository() {
		return svnRepository;
	}

	public void setSvnRepository(SvnRepository svnRepository) {
		this.svnRepository = svnRepository;
	}

	public void setTrunk(String trunk) {
		this.trunk = trunk;
	}

	public void setBranches(Set<String> branches) {
		this.branches = branches;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<String> getBranches() {
		if (branches == null) {
			branches = new HashSet<>();
		}
		return branches;
	}

	public Set<String> getTags() {
		if (tags == null) {
			tags = new HashSet<>();
		}
		return tags;
	}

	public String getTrunk() {
		return trunk;
	}

}
