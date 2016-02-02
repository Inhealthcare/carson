package uk.co.inhealthcare.ops.carson.web.projects;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findAllByOrderByNameAsc();

}
